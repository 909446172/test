package demo.face.plus.faceplus.repository;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.entity.Location;

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/24 15:56
 * @Version 1.0
 */
public interface FacesetInfoRepository {

    UpdateResult deleteFacesetInfo(FacesetInfo facesetInfo);

    List<FacesetInfo> findFacesetInfo(String id);

    UpdateResult updateFacesetInfo(FacesetInfo facesetInfo);

    FacesetInfo insertFacesetInfo(FacesetInfo facesetInfo);

    List<FacesetInfo> findAll();

    List<FacesetInfo> findAll(int logic);

    List<FacesetInfo> findIgnoreLogic();

    boolean increaseSize(FacesetInfo facesetInfo, int mode);

    boolean findAndRemoveFacesetById(String facesetId);
}
