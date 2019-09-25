package demo.face.plus.faceplus.service;

import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.entity.UserInfo;

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/19 19:45
 * @Version 1.0
 */
public interface UserInfoService {

    /**
     *  插入用户信息
     * @param userInfo  用户信息
     * @return 返回被修改的用户信息
     */
    UserInfo insertUserInfo(UserInfo userInfo);

    /**
     * 查找用户信息根据 用户端id
     * @param userId  用户id
     * @return 返回 查找到的用户信息
     */
    UserInfo findByUserId(String userId);

    /**
     *  更新用户faceset
     * @param userid   用户id
     * @param userFaceInfo  用户关联的face信息 和关联的faceset信息
     * @return  更新成功返回true
     */
    boolean updateUserFaceInfoById(String userid, UserFaceInfo userFaceInfo);

    /**
     *根据用户id 和location  返回一个userInfo , 如果用户在数据库中不存在或者 关联face的表不存在 , 则自动创建一个 UserFaceInfo,并与之关联
     * 如果数据库中一条数据都没有 ,则初始化一条 facesetinfo 信息 ;
     * @param userid  用户id
     * @param location  用户位置
     * @return  返回一个有效完整的userinfo ;
     */
    UserInfo repairFaceset(String userid, Location location);


}
