package demo.face.plus.faceplus.service;

import demo.face.plus.faceplus.entity.*;
import demo.face.plus.faceplus.face.entity.RespCreateFaceset;

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/18 16:41
 * @Version 1.0
 */
public interface FacesetInfoService {

    /**
     *  根据 facesetInfo 获得四个
     * @param facesetInfo
     * @return
     */
    List<FacesetInfo> getFourFacesetName(FacesetInfo facesetInfo);

    /**
     *  根据已知的facesetinfo 解析 . 判断是否长度达到上限 , 如果达到上限,将被解析成四个 , 然后根据location 判定一个位置符合的facesetinfo;
     * @param facesetInfo  需要解析的facesetinfo
     * @param location   实际位置
     * @return  如果长度合法 返回null , 如果不合法返回一个facesetinfo
     */
    FacesetInfo analyzeFaceset(FacesetInfo facesetInfo ,Location location);

    /**
     *   根据userinfo对象 判断其是否 关联的faceset数量是否到达上限  ,如果达到上限会 经过解析得到一个可用的 userFaceinfo 对象
     *
     * @param userInfo 用户完整对象 ;
     * @return  可用的用户关联对象
     */
    UserFaceInfo verifiesFaceset(UserInfo userInfo);

    /**
     *  删除全部的faceest 包含face++的faceset , 和数据库中的表信息 ;
     * @return
     */
    boolean deleteAllFaceset();

    /**
     *  查找facesetinfo 根据位置信息, 得到一个可用的faceset
     * @param location  位置信息;
     * @return  返回一个faceset ;
     */
    FacesetInfo findFacesetInfoByLocation(Location location);

    /**
     *  校验facesetInfo  根据位置信息 ,对facesetInfos  这个参数进行效验, 且返回一个faceset
     * @param facesetInfos  faceset集合
     * @param location 位置信息
     * @return  返回一个存在faceset区域覆盖位置的 faceset
     */
    FacesetInfo checkFacesetInfo(List<FacesetInfo> facesetInfos, Location location);


    /**
     *  效验一个faceset 判断是否 符合faceset的位置区域
     * @param facesetInfos  单个faceset
     * @param location  位置信息
     * @return  如果符合位置覆盖区域返回实际值 , 如果无符合返回null
     */
    FacesetInfo checkFacesetInfo(FacesetInfo facesetInfos, Location location);

    /**
     *    在数据库中效验 , 根据位置信息在全局faceset中寻找一个符合位置覆盖的faceset
     * @param location 位置信息
     * @return 符合的faceset 不符合返回null
     */
    FacesetInfo checkFacesetInfo(Location location);

    /**
     *   根据facesetinfo 创建一个faceset, 且在face++ 中创建一个faceest
     * @param outer
     * @return
     */
    FacesetInfo createFacesetForDBAndFaceplus(FacesetInfo outer);





}
