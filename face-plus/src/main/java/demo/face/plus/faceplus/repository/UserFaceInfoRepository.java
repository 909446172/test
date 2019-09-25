package demo.face.plus.faceplus.repository;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.entity.UserFaceInfo;

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/24 16:02
 * @Version 1.0
 */
public interface UserFaceInfoRepository {

    UserFaceInfo findUserFaceInfoById(String id);

    UpdateResult updateFacesetInfoById(String id, String facesetInfoId);

    UpdateResult addFaceInfo(String id, List<FaceInfo> faceInfoList);

    UserFaceInfo insertUserFaceInfo(UserFaceInfo userFaceInfo);

    boolean updateLocation(UserFaceInfo userFaceInfo);

    List<UserFaceInfo> findGeoByLocationAndExtent(Location location , double extent);

}
