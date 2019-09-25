package demo.face.plus.faceplus.controller;

import demo.face.plus.faceplus.entity.FaceInfo;
import demo.face.plus.faceplus.entity.Test;
import demo.face.plus.faceplus.entity.UserFaceInfo;
import demo.face.plus.faceplus.entity.UserInfo;
import demo.face.plus.faceplus.service.FacesetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/23 19:08
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    FacesetInfoService facesetApiService;

    @Autowired
    MongoOperations mongoOperations ;

    @RequestMapping("test2")

    public void test2() {

        applicationEventPublisher.publishEvent(new Test(String.valueOf(1)));

    }

    @RequestMapping("deleteAll")
    public void deleteAllFaceset() {
        boolean b = facesetApiService.deleteAllFaceset();
        mongoOperations.findAllAndRemove(Query.query(Criteria.where("_id").exists(true)), UserInfo.class);
        mongoOperations.findAllAndRemove(Query.query(Criteria.where("_id").exists(true)), UserFaceInfo.class);
        List<FaceInfo> id = mongoOperations.findAllAndRemove(Query.query(Criteria.where("_id").exists(true)), FaceInfo.class);
        System.out.println(id);
        System.out.println(b);

    }

}
