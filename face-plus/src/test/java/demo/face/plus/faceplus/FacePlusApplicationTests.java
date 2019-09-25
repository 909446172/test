package demo.face.plus.faceplus;

import com.mongodb.client.result.UpdateResult;
import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.*;
import demo.face.plus.faceplus.service.FaceInfoService;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import demo.face.plus.faceplus.service.UserInfoService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacePlusApplicationTests {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    FaceInfoService faceInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void addUserInfo() {
        userInfoService.insertUserInfo(new UserInfo("5"));
    }

    @Test
    public void findBecomingPosition() {
        FacesetInfo facesetInfo = new FacesetInfo("345", "112", 0, new AreaLimit(360d, 180d, 1), new Location(123, 50), 1, new Location(0, 0), new Date(), new Date());

        List<UserFaceInfo> userFaceInfos = new ArrayList<>();
        Map<Object, Location> areaPosition = userFaceInfoService.getAreaPosition(facesetInfo); //根据新的newFacesetInfo 获得其所在有效覆盖区域
        Box box = new Box(new Point(areaPosition.get("first").getLongitude(), areaPosition.get("first").getLatitude()), new Point(areaPosition.get("second").getLongitude(), areaPosition.get("second").getLatitude()));

        List<UserFaceInfo> userFaceInfos1 = mongoOperations.find(Query.query(new Criteria().andOperator(
               Criteria.where("facesetInfo.$id").is(new ObjectId("5d8585613b657737c085d64b")),
         //       Criteria.where("_id").is("5d8585613b657737c085d64c")//,

              Criteria.where("logic").is(1), Criteria.where("location").within(box)
        )
        ), UserFaceInfo.class);   //查询出全部的符合newfaceset位置的全部UserFaceInfo;

        System.out.println(userFaceInfos1);



    }

    @Test
    public void dropTest() {

        mongoOperations.dropCollection("facesetInfo");
    }

    @Test
    public void insertUserInfo() {
        UserFaceInfo userFaceInfo = new UserFaceInfo();
        userFaceInfo.setId("5d82fd963b657719f8054ed0");
        mongoOperations.insert(new UserInfo("1", userFaceInfo));

    }

    @Test
    public void transferFaceInfoTest() {
        FaceInfo faceInfo = new FaceInfo("3", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date());
        FaceInfo faceInfo2 = new FaceInfo("4", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date());

        FaceInfo insert = mongoOperations.insert(faceInfo);
        FaceInfo insert1 = mongoOperations.insert(faceInfo2);
        System.out.println(faceInfo);

    }

    @Test
    public void insertUserFaceInfoTest2() {
        FaceInfo faceInfo = new FaceInfo("3", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date());
        FaceInfo faceInfo2 = new FaceInfo("3", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date());
        FacesetInfo facesetInfo = new FacesetInfo("345", "112", 0, new AreaLimit(360d, 180d, 1), new Location(123, 50), 1, new Location(123, 50), new Date(), new Date());
        List<FaceInfo> faceInfos = Arrays.asList(faceInfo, faceInfo2);
        UserFaceInfo userFaceInfo = new UserFaceInfo(facesetInfo, faceInfos, 1, new Location(0, 0));
        UserFaceInfo save = mongoOperations.save(userFaceInfo);

        System.out.println(save);
    }



    @Test
    public void insertUserFaceInfoTest1() {
        String id = "5d84ad7e3b6577309807c2bf";
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("aa"));

        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(
                new Criteria().andOperator(Criteria.where("_id").is(id))
                        .orOperator(Criteria.where("logic").is(0))
        ), Update.update("faceInfo", list), UserFaceInfo.class);
    }


    public void testUser() {


    }


}
