package demo.face.plus.faceplus.repository.repositoryImpl;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.entity.UserInfo;
import demo.face.plus.faceplus.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @Author zyy
 * @Date 2019/9/24 16:03
 * @Version 1.0
 */
@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    MongoOperations mongoOperations;


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
}
