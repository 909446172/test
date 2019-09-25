package demo.face.plus.faceplus.repository.repositoryImpl;

import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.repository.FaceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

/**
 * @Author zyy
 * @Date 2019/9/24 16:01
 * @Version 1.0
 */
@Repository
public class FaceInfoRepositoryImpl implements FaceInfoRepository {


    @Autowired
    MongoOperations mongoOperations;

    @Override
    public FaceInfo insertFaceInfo(FaceInfo faceInfo) {
        if (ObjectUtils.isEmpty(faceInfo)) {
            return null;
        }
        FaceInfo insert = mongoOperations.insert(faceInfo);
        if (ObjectUtils.isEmpty(insert)) {
            return null;
        }
        return insert;
    }
}
