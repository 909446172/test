package demo.face.plus.faceplus.service.serviceImpl;

import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.*;
import demo.face.plus.faceplus.face.api.RestFaceApiImpl;
import demo.face.plus.faceplus.face.entity.RequCreateFaceset;
import demo.face.plus.faceplus.face.entity.RequDeleteFaceset;
import demo.face.plus.faceplus.repository.FacesetInfoRepository;
import demo.face.plus.faceplus.repository.UserFaceInfoRepository;
import demo.face.plus.faceplus.repository.UserInfoRepository;
import demo.face.plus.faceplus.service.FacesetInfoService;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import demo.face.plus.faceplus.service.UserInfoService;
import demo.face.plus.faceplus.util.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/18 16:44
 * @Version 1.0
 */
@Service
public class FacesetInfoServiceImpl implements FacesetInfoService {

    @Autowired
    RestFaceApiImpl restFaceApi;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    FacesetInfoRepository facesetInfoRepository;

    @Autowired
    @Lazy
    UserFaceInfoService userFaceInfoService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    UserFaceInfoRepository userFaceInfoRepository;

    /**
     * 根据已知的facesetinfo 推算 需要划分的四个地理位置作为拆分faceset的命名;
     *
     * @param facesetInfo
     * @return
     */
    @Override
    public List<FacesetInfo> getFourFacesetName(FacesetInfo facesetInfo) {
        // 根据 faceset 当 faceset数量到达9000时进入这里 重新创建四个faceset 将原来这个删除 , 且将四个faceset里面的face进行区块划分
        if (ObjectUtils.isEmpty(facesetInfo) || facesetInfo.getSize() < Integer.valueOf(faceSecretConfig.getFacesetSuperiorLimit())) {
            return null;
        }
        //根据现有的坐标 ,以及所在区域 , 所在面积 , 划分faceset
        Location leftTopAxes;
        Location leftBottomAxes;
        Location rightTopAxes;
        Location rightBottomAxes;
        Location beforeLocation = facesetInfo.getBeforeLocation();
        Location location = facesetInfo.getLocation();
        AreaLimit areaLimit = facesetInfo.getAreaLimit();
        AreaLimit areaLimit1 = new AreaLimit(areaLimit.getLongitude() / 2.0, areaLimit.getLatitude() / 2.0, areaLimit.getPosition());
        // 根据坐标得到四个位置;
        leftTopAxes = new Location(location.getLongitude() + (areaLimit1.getLongitude() / 2), location.getLatitude() - (areaLimit1.getLatitude() / 2));
        leftBottomAxes = new Location(location.getLongitude() - (areaLimit1.getLongitude() / 2), location.getLatitude() - (areaLimit1.getLatitude() / 2));
        rightTopAxes = new Location(location.getLongitude() + (areaLimit1.getLongitude() / 2), location.getLatitude() + (areaLimit1.getLatitude() / 2));
        rightBottomAxes = new Location(location.getLongitude() - (areaLimit1.getLongitude() / 2), location.getLatitude() + (areaLimit1.getLatitude() / 2));
        //位置划分为 从左 1 2 到 右 3 4

        FacesetInfo facesetInfo1 = new FacesetInfo(faceSecretConfig.getFacePrefix() + "_" + leftTopAxes.getLongitude() + "_" + leftTopAxes.getLatitude(), 0, new AreaLimit(areaLimit1.getLongitude(), areaLimit1.getLatitude(), 1), location, 1, leftTopAxes);
        FacesetInfo facesetInfo2 = new FacesetInfo(faceSecretConfig.getFacePrefix() + "_" + leftBottomAxes.getLongitude() + "_" + leftBottomAxes.getLatitude(), 0, new AreaLimit(areaLimit1.getLongitude(), areaLimit1.getLatitude(), 2), location, 1, leftBottomAxes);
        FacesetInfo facesetInfo3 = new FacesetInfo(faceSecretConfig.getFacePrefix() + "_" + rightTopAxes.getLongitude() + "_" + rightTopAxes.getLatitude(), 0, new AreaLimit(areaLimit1.getLongitude(), areaLimit1.getLatitude(), 3), location, 1, rightTopAxes);
        FacesetInfo facesetInfo4 = new FacesetInfo(faceSecretConfig.getFacePrefix() + "_" + rightBottomAxes.getLongitude() + "_" + rightBottomAxes.getLatitude(), 0, new AreaLimit(areaLimit1.getLongitude(), areaLimit1.getLatitude(), 4), location, 1, rightBottomAxes);
        List<FacesetInfo> facesetInfos = Arrays.asList(facesetInfo1, facesetInfo2, facesetInfo3, facesetInfo4);
        return facesetInfos;
    }

    /**
     * 创建facset ,根据已知的
     *
     * @param facesetInfo 根据现有的这个facesetinfo 去face++ 创建出4个区块划分的faceset,且将face 全部转移到被覆盖到的faceset中去
     * @return 根据facesetInfo size大于指定数量 , 那么解析成四个方位的faceset , 且返回自身合适的那一个 如果为空的话表示自身的faceset是合适的;
     */
    @Override
    public FacesetInfo analyzeFaceset(FacesetInfo facesetInfo,Location location) {
        List<FacesetInfo> fourFacesetName = getFourFacesetName(facesetInfo);
        if (ObjectUtils.isEmpty(fourFacesetName)) {
            return null;
        }
        for (FacesetInfo facesetInfo1 : fourFacesetName) {
            if (facesetInfo1.getName() == null) {
                return null;
            }
            FacesetInfo facesetForDBAndFaceplus = createFacesetForDBAndFaceplus(facesetInfo1);
            //  将 老的faceset里面的对象  能够被新的位置区域覆盖的 全部查找出来 , 然后将用户信息的faceset 换成 新的faceset 的id ;
           List<UserFaceInfo> userFaceInfos = userFaceInfoService.transferFaceInfo(facesetInfo, facesetForDBAndFaceplus);
        }
        FacesetInfo facesetInfo1 = checkFacesetInfo(fourFacesetName,location); // 从解析的四个钟找到一个合适的faceset;\
        if (facesetInfo1 == null) {  //如果等于null表示 地理位置和以前的不一样  , 将;
            facesetInfo1 =  checkFacesetInfo(location);   //从数据库中挑选一个合适的faceset ;
            userFaceInfoService.transferFaceInfo(facesetInfo, facesetInfo1);  //转移faceinfo;
        }
        userFaceInfoRepository.updateFacesetInfoById(facesetInfo.getId(), facesetInfo1.getId());  // 将用户的faceset换成 合适的faceset;
        facesetInfo.setLogic(0); //对原有的作逻辑删除
        facesetInfoRepository.deleteFacesetInfo(facesetInfo);
        return facesetInfo1;
    }

    /**
     * 根据位置信息 , 返回一个有效的 faceset , 如果没有则初始化一个 , 如果有多个faceset 判断这个 位置信息处在哪一个faceset的覆盖区域
     *
     * @param userInfo
     * @return
     */
    @Override
    public UserFaceInfo verifiesFaceset(UserInfo userInfo) {
        FacesetInfo facesetInfo1 = analyzeFaceset(userInfo.getUserFaceInfo().getFacesetInfo(),userInfo.getUserFaceInfo().getLocation());
        if (facesetInfo1 != null) {
            UserFaceInfo userFaceInfo = userInfo.getUserFaceInfo();
            userFaceInfo.setFacesetInfo(facesetInfo1);
            return userFaceInfo;
        }
        return userInfo.getUserFaceInfo();
    }

    @Override
    public boolean deleteAllFaceset() {
        List<FacesetInfo> all = facesetInfoRepository.findIgnoreLogic();
        for (FacesetInfo facesetInfo : all) {
                    System.out.println("---------------");
                    try {
                    restFaceApi.deleteFaceset(new RequDeleteFaceset(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), facesetInfo.getName(), 0));
                        System.out.println("-------"+facesetInfo.getId()+"--------");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                   facesetInfoRepository.findAndRemoveFacesetById(facesetInfo.getId());
                    ThreadUtils.sleepThread(100L);


        }
        if (ObjectUtils.isEmpty(all)) {
            return false;
        }
        return true;
    }

    @Override
    public FacesetInfo findFacesetInfoByLocation(Location location) {
        if (ObjectUtils.isEmpty(location)) {
            return null;
        }
        List<FacesetInfo> all = facesetInfoRepository.findAll();
        //如果不为空表示 有符合的faceset
        FacesetInfo facesetInfo = checkFacesetInfo(all, location);
        if (!ObjectUtils.isEmpty(facesetInfo)) {
            FacesetInfo facesetInfos = analyzeFaceset(facesetInfo,location);  //解析faceset 判断是否到达上限,如果达到上限,解析为四个;
            if (!ObjectUtils.isEmpty(facesetInfos)) { //如果进入表示 faceset达到上限被解析成四个;
                return facesetInfos;  //如果全部条件都满足的话 , 直接返回合适的faceset;
            } else {
                return facesetInfo;
            }
        }
        return null;
    }

    /**
     *  根据 位置信息找到 在facesetInfos 中 在目标区域位置中的哪一个 ;
     * @param facesetInfos
     * @param location
     * @return
     */
    @Override
    public FacesetInfo checkFacesetInfo(List<FacesetInfo> facesetInfos,Location location){
        for(FacesetInfo facesetInfo:facesetInfos){
            Location firstLocation=new Location((facesetInfo.getAreaLimit().getLongitude()/2)+(facesetInfo.getLocation().getLongitude()),(facesetInfo.getLocation().getLatitude())-(facesetInfo.getAreaLimit().getLatitude()/2));
            Location secondLocation=new Location((facesetInfo.getLocation().getLongitude())-(facesetInfo.getAreaLimit().getLongitude()/2),(facesetInfo.getLocation().getLatitude())+(facesetInfo.getAreaLimit().getLatitude()/2));
            if(location.getLongitude()<=firstLocation.getLongitude()&&location.getLatitude()>firstLocation.getLatitude()&&location.getLongitude()>secondLocation.getLongitude()&&location.getLatitude()<=secondLocation.getLatitude()){
                //当达到条件表示 目标所在区域符合条件
                return facesetInfo;
            }
        }
        return null;
    }



    @Override
    public FacesetInfo checkFacesetInfo(FacesetInfo facesetInfos,Location location){
        List<FacesetInfo> list = new ArrayList<>();
        list.add(facesetInfos);
        FacesetInfo facesetInfo = checkFacesetInfo(list, location);
        return facesetInfo;
    }

    /**
     * 根据位置信息 找到一个合适的faceset;
     * @param location
     * @return
     */
    @Override
    public FacesetInfo checkFacesetInfo(Location location){
        List<FacesetInfo> all = facesetInfoRepository.findAll();
        if (ObjectUtils.isEmpty(all)) {
            String outer = faceSecretConfig.getFacePrefix() + "_" + 0 + "_" + 0;
            FacesetInfo facesetInfo = new FacesetInfo(outer, 0, new AreaLimit(360D, 180D, 1), new Location(0, 0), 1, new Location(0, 0));
            createFacesetForDBAndFaceplus(facesetInfo);  //如果数据库为空
            return facesetInfo;
        }
        FacesetInfo facesetInfo = checkFacesetInfo(all, location);
        return facesetInfo;
    }

    @Override
    public FacesetInfo createFacesetForDBAndFaceplus(FacesetInfo facesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return null;
        }
        applicationEventPublisher.publishEvent(new RequCreateFaceset(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), facesetInfo.getName(), facesetInfo.getName(), facesetInfo.getName(), null, null, 1));
        FacesetInfo insert = facesetInfoRepository.insertFacesetInfo(facesetInfo);
        if (ObjectUtils.isEmpty(insert)) {
            return null;
        }
        return insert;
    }

}
