package demo.face.plus.faceplus.service.serviceImpl;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.*;
import demo.face.plus.faceplus.repository.UserFaceInfoRepository;
import demo.face.plus.faceplus.service.FacesetInfoService;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import demo.face.plus.faceplus.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/19 19:46
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    FacesetInfoService facesetInfoService;

    @Autowired
    UserFaceInfoRepository userFaceInfoRepository;

    @Override
    public UserInfo insertUserInfo(UserInfo userInfo) {
        if (ObjectUtils.isEmpty(userInfo)) {
            return null;
        }
        UserInfo insert = mongoOperations.insert(userInfo);
        return insert;
    }

    @Override
    public UserInfo findByUserId(String userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return null;
        }
        UserInfo userId2 = mongoOperations.findOne(Query.query(Criteria.where("userId").is(userId)), UserInfo.class);
        return userId2;
    }

    @Override
    public boolean updateUserFaceInfoById(String userid, UserFaceInfo userFaceInfo) {
        if (ObjectUtils.isEmpty(userFaceInfo)&& StringUtils.isEmpty(userid)) {
            return false;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(Criteria.where("userId").is(userid)), Update.update("userFaceInfo", userFaceInfo), UserInfo.class);
        if (updateResult.getModifiedCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     *  效验用户且返回一个正确的userInfo
     * @param userid
     * @param location
     * @return
     */
    @Override
    public UserInfo repairFaceset(String userid, Location location) {
        if (ObjectUtils.isEmpty(userid)) {
            return null;
        }
        UserInfo byUserId = findByUserId(userid);
        if (ObjectUtils.isEmpty(byUserId)) {  //如果用户等于null  插入一条用户信息;
            UserInfo userInfo = new UserInfo(userid);
            mongoOperations.insert(userInfo);
            byUserId=userInfo;
        }
        if (ObjectUtils.isEmpty(byUserId.getUserFaceInfo())) {  //如果等于UserFacesetInfo 为空的话 , 根据location 创建一个合适的
            FacesetInfo facesetInfo = facesetInfoService.checkFacesetInfo(location);
            List<FaceInfo> faceInfos = new ArrayList<>();
            UserFaceInfo userFaceInfo = new UserFaceInfo(facesetInfo, faceInfos, 1, location);
            userFaceInfo= userFaceInfoRepository.insertUserFaceInfo(userFaceInfo);
            boolean b = updateUserFaceInfoById(userid, userFaceInfo);
            if (b) {
                byUserId.setUserFaceInfo(userFaceInfo);
            }
            return byUserId;
        }else {
            //如果存在用户信息 , 将用户的位置信息更新;
            UserFaceInfo userFaceInfo = byUserId.getUserFaceInfo();
            userFaceInfo.setLocation(location);
            userFaceInfoService.updateLocation(userFaceInfo);
        }
        return byUserId;
    }

}
