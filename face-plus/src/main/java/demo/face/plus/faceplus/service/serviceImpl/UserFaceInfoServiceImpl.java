package demo.face.plus.faceplus.service.serviceImpl;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.RequAddFace;
import demo.face.plus.faceplus.repository.FaceInfoRepository;
import demo.face.plus.faceplus.repository.FacesetInfoRepository;
import demo.face.plus.faceplus.repository.UserFaceInfoRepository;
import demo.face.plus.faceplus.service.FaceInfoService;
import demo.face.plus.faceplus.service.FacesetInfoService;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import demo.face.plus.faceplus.service.UserInfoService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/19 9:38
 * @Version 1.0
 */
//@Slf4j
@Service
public class UserFaceInfoServiceImpl implements UserFaceInfoService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    FaceInfoService faceInfoService;

    @Autowired
    FaceInfoRepository faceInfoRepository;

    @Autowired
    FacesetInfoRepository facesetInfoRepository;

    @Autowired
    RestFaceApi restFaceApi;

    @Autowired
    FacesetInfoService facesetInfoService;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    UserFaceInfoRepository userFaceInfoRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


    /**
     * 将 老的faceset里面的对象  能够被新的位置区域覆盖的 全部查找出来 , 然后将用户信息的faceset 换成 新的faceset 的name ;
     *
     * @param facesetInfo
     * @return
     */
    @Override
    public List<UserFaceInfo> transferFaceInfo(FacesetInfo facesetInfo, FacesetInfo newFacesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo) && ObjectUtils.isEmpty(newFacesetInfo)) {
            return null;
        }
        Map<Object, Location> areaPosition = getAreaPosition(newFacesetInfo); //根据新的newFacesetInfo 获得其所在有效覆盖区域
        //Box box = new Box(new Point(areaPosition.get("first").getLongitude(), areaPosition.get("first").getLatitude()), new Point(areaPosition.get("second").getLongitude(), areaPosition.get("second").getLatitude()));
        List<UserFaceInfo> userFaceInfos1 = mongoOperations.find(Query.query(new Criteria().andOperator(Criteria.where("facesetInfo.$id").is(new ObjectId(facesetInfo.getId())),
                Criteria.where("logic").is(1)//, Criteria.where("location").within(box)
        )), UserFaceInfo.class);   //查询出全部的符合newfaceset位置的全部UserFaceInfo;
        if (userFaceInfos1.size() > 0) {
            for (UserFaceInfo userFaceInfo : userFaceInfos1) {
                FacesetInfo facesetInfo1 = facesetInfoService.checkFacesetInfo(newFacesetInfo, userFaceInfo.getLocation());
                if (facesetInfo1 != null) { //如果不为空 表示是在哪个区域 ,将userFaceInfo里面的face从 face++里面 移动到 newFacesetInfo里面去;
                    UpdateResult updateResult = userFaceInfoRepository.updateFacesetInfoById(userFaceInfo.getId(), newFacesetInfo.getId());  //将用户userfaceset更新为符合条件的那个一 faceset
                    if (updateResult.getModifiedCount() > 0) {  //如果操作成功,则将face++里面添加 新的faceInfo
                        for (FaceInfo faceInfo : userFaceInfo.getFaceInfo()) {
                            //添加faceinfo 到face++;
                            applicationEventPublisher.publishEvent(new RequAddFace(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), newFacesetInfo.getName(), faceInfo.getToken()));
                            facesetInfoRepository.increaseSize(newFacesetInfo, 1); //每添加一个 , 对数据库里面的size ++ ;
                        }
                    }
                }
            }
        }
        return userFaceInfos1;
    }

    /**
     * 将faceInfoList 添加到 UserFaceInfo里面
     *
     * @param userFaceInfoid
     * @param faceInfoList
     * @return
     */
    @Override
    public UserFaceInfo addFaceInfo(String userFaceInfoid, List<FaceInfo> faceInfoList) {
        if (ObjectUtils.isEmpty(faceInfoList)) {
            return null;
        }
        UserFaceInfo byId = userFaceInfoRepository.findUserFaceInfoById(userFaceInfoid); //查找出UserFaceInfo的具体信息
        List<FaceInfo> faceInfos = new ArrayList<>();
        for (FaceInfo faceInfo : faceInfoList) {
            FaceInfo faceInfo1 = faceInfoRepository.insertFaceInfo(faceInfo);  //添加faceinfo到数据库
            applicationEventPublisher.publishEvent(new RequAddFace(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), byId.getFacesetInfo().getName(), faceInfo1.getToken()));
            facesetInfoRepository.increaseSize(byId.getFacesetInfo(), 1);
            faceInfos.add(faceInfo1);  // TODO: 2019/9/20   这里的faceInfo可以变成只放置id,这样数据库就只显示关联id;
        }
        @NonNull List<FaceInfo> faceInfo = byId.getFaceInfo();
        boolean b = faceInfo.addAll(faceInfos);
        if (b) {
            byId.setFaceInfo(faceInfo);
        }
        UpdateResult updateResult = userFaceInfoRepository.addFaceInfo(userFaceInfoid, faceInfo);
        if (updateResult.getModifiedCount() > 0) {
            return byId;
        }
        return null;
    }


    @Override
    public Map<Object, Location> getAreaPosition(FacesetInfo facesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return null;
        }
        Location firstLocation = new Location((facesetInfo.getAreaLimit().getLongitude() / 2) + (facesetInfo.getLocation().getLongitude()), (facesetInfo.getLocation().getLatitude()) - (facesetInfo.getAreaLimit().getLatitude() / 2));
        Location secondLocation = new Location((facesetInfo.getLocation().getLongitude()) - (facesetInfo.getAreaLimit().getLongitude() / 2), (facesetInfo.getLocation().getLatitude()) + (facesetInfo.getAreaLimit().getLatitude() / 2));
        if (ObjectUtils.isEmpty(firstLocation) && ObjectUtils.isEmpty(secondLocation)) {
            return null;
        }
        Map<Object, Location> map = new HashMap<>();
        map.put("first", firstLocation);
        map.put("second", secondLocation);
        return map;
    }

    @Override
    public boolean updateLocation(UserFaceInfo userFaceInfo) {
        if (ObjectUtils.isEmpty(userFaceInfo)) {
            return false;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(Criteria.where("id").is(userFaceInfo.getId())), Update.update("location", userFaceInfo.getLocation()), UserFaceInfo.class);
        if (updateResult.getModifiedCount()>0) {
            return true;
        }
        return false;

    }


    @Override
    public  List<UserFaceInfo> findHoneyFaceInfoOfNear(Location location,double extent,String imgurl) {
        List<UserFaceInfo> geoByLocationAndExtent = userFaceInfoRepository.findGeoByLocationAndExtent(location, extent); // 返回指定区域的用户关联
        
        

        return null;
    }

}
