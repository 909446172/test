package demo.face.plus.faceplus.event;

import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.FacesetInfo;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.RequCreateFaceset;
import demo.face.plus.faceplus.face.entity.RespCreateFaceset;
import demo.face.plus.faceplus.util.ThreadUtils;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author zyy
 * @Date 2019/9/23 14:01
 * @Version 1.0
 */
@Component
@Slf4j
public class FacesetEvent {

    @Autowired
    RestFaceApi restFaceApi;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Async
    @Synchronized
    @EventListener
    public void createFaceset(RequCreateFaceset requCreateFaceset) {
        log.debug("createFaceset method!   requCreateFaceset:"+requCreateFaceset);
        RespCreateFaceset faceset = restFaceApi.createFaceset(requCreateFaceset);
        ThreadUtils.sleepThread(Long.valueOf(faceSecretConfig.getInterval()));
    }



}
