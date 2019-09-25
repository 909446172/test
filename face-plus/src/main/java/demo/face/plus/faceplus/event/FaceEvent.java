package demo.face.plus.faceplus.event;

import com.alibaba.fastjson.JSONObject;
import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.Test;
import demo.face.plus.faceplus.face.FaceUrl;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.RequAddFace;
import demo.face.plus.faceplus.util.HttpClientUtils;
import demo.face.plus.faceplus.util.ThreadUtils;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/23 14:01
 * @Version 1.0
 */
@Component
@Slf4j
public class FaceEvent {
    public static final Object look = new Object();
    @Autowired
    RestFaceApi restFaceApi;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    RestTemplate restTemplate;

    @Async
    @Synchronized
    @EventListener
    public void addFace(RequAddFace requAddFace) {
        log.debug("addFace method! requAddFace:" + requAddFace);
        ThreadUtils.sleepThread(Long.valueOf(faceSecretConfig.getInterval()));
        restFaceApi.addFace(requAddFace);
    }

    @Async
    @EventListener
    @Synchronized
    public void test(Test t) {

            System.out.println(this.getClass());
            log.info("---------------------------------" + t);
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
            MultiValueMap multiValueMap = new LinkedMultiValueMap();
            multiValueMap.add("api_key", faceSecretConfig.getApiKey());
            multiValueMap.add("api_secret", faceSecretConfig.getApiSecret());
            multiValueMap.add("outer_id", "face_0_0");
         //   ResponseEntity<String> post =  restTemplate.postForEntity(FaceUrl.url_faceset_detail, multiValueMap, String.class);

         Map map = multiValueMap.toSingleValueMap();
           Object o = JSONObject.toJSON(map);
        String s = HttpClientUtils.doPost(FaceUrl.url_faceset_detail, map);
        System.out.println(s);

        ThreadUtils.sleepThread(Long.valueOf(faceSecretConfig.getInterval()));


    }

}
