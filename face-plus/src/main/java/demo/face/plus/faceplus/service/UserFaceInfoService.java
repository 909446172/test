package demo.face.plus.faceplus.service;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.entity.UserFaceInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/19 9:12
 * @Version 1.0
 */
public interface UserFaceInfoService {

    /**
     *   转移face信息 ,  从facesetinfo 中 转移到newFacesetInfo 中 , 且在face++ 中转移
     * @param facesetInfo  老的faceset
     * @param newFacesetInfo  新的faceset
     * @return  返回实际存在的关联对象 ,
     */
    List<UserFaceInfo> transferFaceInfo(FacesetInfo facesetInfo, FacesetInfo newFacesetInfo);

    /**
     *  通过一个facesetinfo 对象 ,获得他的所在面积区域
     * @param facesetInfo 所在操作的facesetinfo
     * @return  返回两点坐标
     */
    Map<Object, Location> getAreaPosition(FacesetInfo facesetInfo);

    /**
     * 更新用户关联表的位置信息
     * @param userFaceInfo 用户关联表
     * @return 如果更新成功返回true
     */
    boolean updateLocation(UserFaceInfo userFaceInfo);

    /**
     *  添加face 信息 ,
     * @param userFaceInfoid 用户的关联表的id
     * @param faceInfoList 所要添加的faceinfo 集合
     * @return 返回具体被修改完成后的关联信息对象
     */
    UserFaceInfo addFaceInfo(String userFaceInfoid, List<FaceInfo> faceInfoList);

    /**
     *
     * @return
     */
    List<UserFaceInfo> findHoneyFaceInfoOfNear(Location location,double extent,String imgurl);

}
