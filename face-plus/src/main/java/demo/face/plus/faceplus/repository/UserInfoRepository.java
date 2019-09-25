package demo.face.plus.faceplus.repository;

import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.entity.UserInfo;

/**
 * @Author zyy
 * @Date 2019/9/24 16:03
 * @Version 1.0
 */
public interface UserInfoRepository {

    UserInfo insertUserInfo(UserInfo userInfo);

    UserInfo findByUserId(String userId);

    boolean updateUserFaceInfoById(String userid, UserFaceInfo userFaceInfo);

}
