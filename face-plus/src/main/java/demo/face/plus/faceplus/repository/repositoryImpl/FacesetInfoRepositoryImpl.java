package demo.face.plus.faceplus.repository.repositoryImpl;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.repository.FacesetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/24 15:57
 * @Version 1.0
 */
@Repository
public class FacesetInfoRepositoryImpl implements FacesetInfoRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public UpdateResult deleteFacesetInfo(FacesetInfo facesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return null;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(Criteria.where("_id").is(facesetInfo.getId())), Update.update("logic", facesetInfo.getLogic()), FacesetInfo.class);
        if (ObjectUtils.isEmpty(updateResult)) {
            return null;
        }
        return updateResult;
    }

    @Override
    public List<FacesetInfo> findFacesetInfo(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        List<FacesetInfo> facesetInfos = mongoOperations.find(Query.query(new Criteria()
                .andOperator(Criteria.where("id").is(id).andOperator(Criteria.where("logic").is(1)))
        ), FacesetInfo.class);
        if (ObjectUtils.isEmpty(facesetInfos)) {
            return null;
        }
        return facesetInfos;

    }

    @Override
    public UpdateResult updateFacesetInfo(FacesetInfo facesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return null;
        }
        UpdateResult upsert = mongoOperations.updateFirst(Query.query(new Criteria().andOperator(Criteria.where("id").is(facesetInfo.getId()))
                        .andOperator(Criteria.where("logic").is(1))),
                Update.update("name", facesetInfo.getName()).set("beforeLocation", facesetInfo.getBeforeLocation())
                        .set("areaLimit", facesetInfo.getAreaLimit())
                        .set("logic", facesetInfo.getLogic()).set("location", facesetInfo.getLocation()).set("size", facesetInfo.getSize())
                , FacesetInfo.class);
        if (ObjectUtils.isEmpty(upsert)) {
            return null;
        }
        return upsert;
    }

    @Override
    public FacesetInfo insertFacesetInfo(FacesetInfo facesetInfo) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return null;
        }
        FacesetInfo insert = mongoOperations.insert(facesetInfo);
        if (ObjectUtils.isEmpty(insert)) {
            return null;
        }
        return insert;
    }

    @Override
    public List<FacesetInfo> findAll() {
        List<FacesetInfo> all = mongoOperations.find(Query.query(Criteria.where("logic").is(1)), FacesetInfo.class);
        return all;
    }

    @Override
    public List<FacesetInfo> findAll(int logic) {
        List<FacesetInfo> all = mongoOperations.find(Query.query(Criteria.where("logic").is(logic)), FacesetInfo.class);
        return all;
    }

    @Override
    public List<FacesetInfo> findIgnoreLogic() {
        List<FacesetInfo> all = mongoOperations.findAll( FacesetInfo.class);
        return all;
    }

    @Override
    public boolean increaseSize(FacesetInfo facesetInfo, int mode) {
        if (ObjectUtils.isEmpty(facesetInfo)) {
            return false;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(new Criteria().andOperator(Criteria.where("id").is(facesetInfo.getId())
                .andOperator(Criteria.where("logic").is(1)))), new Update().inc("size", mode), FacesetInfo.class);
        if (updateResult.getModifiedCount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean findAndRemoveFacesetById(String facesetId) {
        FacesetInfo id = mongoOperations.findAndRemove(Query.query(Criteria.where("id").is(facesetId)), FacesetInfo.class);
        if (ObjectUtils.isEmpty(id)) {
            return true;
        }
        return false;
    }
}
