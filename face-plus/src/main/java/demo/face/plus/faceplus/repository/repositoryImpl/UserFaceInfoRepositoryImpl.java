package demo.face.plus.faceplus.repository.repositoryImpl;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.entity.Location;
import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.repository.UserFaceInfoRepository;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/24 16:02
 * @Version 1.0
 */
@Slf4j
@Repository
public class UserFaceInfoRepositoryImpl implements UserFaceInfoRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public UserFaceInfo findUserFaceInfoById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        UserFaceInfo byId = mongoOperations.findById(id, UserFaceInfo.class);
        if (ObjectUtils.isEmpty(byId)) {
            return null;
        }
        return byId;
    }

    @Override
    public UpdateResult updateFacesetInfoById(String id, String facesetInfoId) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(Criteria.where("id").is(id)), Update.update("facesetInfo.$id", new ObjectId(facesetInfoId)), UserFaceInfo.class);
        return updateResult;
    }

    @Override
    public UpdateResult addFaceInfo(String id, List<FaceInfo> faceInfoList) {
        if (StringUtils.isEmpty(id)&&ObjectUtils.isEmpty(faceInfoList)) {
            return null;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(
                new Criteria().andOperator(Criteria.where("_id").is(id),
                        Criteria.where("logic").is(1)
                )), Update.update("faceInfo", faceInfoList), UserFaceInfo.class);

        return updateResult;
    }

    @Override
    public UserFaceInfo insertUserFaceInfo(UserFaceInfo userFaceInfo) {
        if (ObjectUtils.isEmpty(userFaceInfo)) {
            return null;
        }
        UserFaceInfo insert = mongoOperations.insert(userFaceInfo);
        return insert;
    }

    @Override
    public boolean updateLocation(UserFaceInfo userFaceInfo) {
        if (ObjectUtils.isEmpty(userFaceInfo)) {
            return false;
        }
        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(Criteria.where("id").is(userFaceInfo.getId())), Update.update("location", userFaceInfo.getLocation()), UserFaceInfo.class);
        if (updateResult.getModifiedCount()>0) {
            return true;
        }
        return false;

    }

    @Override
    public  List<UserFaceInfo> findGeoByLocationAndExtent(Location location , double extent) {
        if (ObjectUtils.isEmpty(location)&& extent<0.0) {
            return null;
        }
        Box box = new Box(new Point(location.getLongitude()+extent, location.getLatitude()-extent), new Point(location.getLongitude()-extent, location.getLatitude()+extent));
        List<UserFaceInfo> userFaceInfos = mongoOperations.find(new Query(Criteria.where("location").within(box)), UserFaceInfo.class);
        return userFaceInfos;
    }
}
