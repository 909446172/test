package demo.face.plus.faceplus;

import com.alibaba.fastjson.JSONObject;
import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.exception.FaceApiException;
import demo.face.plus.faceplus.face.FaceUrl;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.*;
import demo.face.plus.faceplus.service.FacesetInfoService;
import demo.face.plus.faceplus.util.RestTemplateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/20 14:03
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class HttpPlusTest {

    @Autowired
    RestFaceApi restFaceApi;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    FacesetInfoService facesetApiService;

    @Test
    public void detectTest() {
        String image = "https://z909446172.oss-cn-shenzhen.aliyuncs.com/image/facePicture.jpg";
        RespDetect detect = restFaceApi.detect(new RequDetect(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), image, 0));
        System.out.println(detect);
    }



    @Test
    public void detectTest1() {
        String image = "https://z909446172.oss-cn-shenzhen.aliyuncs.com/image/facePicture.jpg";
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        Map map = new HashMap();
        map.put("api_key", faceSecretConfig.getApiKey());
        map.put("api_secret", faceSecretConfig.getApiSecret());
        map.put("image_url", image);
        MultiValueMap<String, String> map1 = new LinkedMultiValueMap<>();
        //   ResponseEntity<Object> post = RestTemplateUtils.post(url, map, Object.class);
        RestTemplate restTemplate = new RestTemplate();
        map1.setAll(map);
        Object o = restTemplate.postForObject(url, map1, Object.class);

        System.out.println(o);


    }

    @Test
    public void createFaceTest() {
        String name = "face_-90_-222";
        RespCreateFaceset faceset = restFaceApi.createFaceset(new RequCreateFaceset(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), name, name, name, null, null, 1));
        System.out.println(faceset);
    }

    @Test
    public void testface() {
        String image = "https://z909446172.oss-cn-shenzhen.aliyuncs.com/image/facePicture.jpg";
        RequDetect requDetect = new RequDetect(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), image, 0);
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key", requDetect.getApi_key());
        multiValueMap.add("api_secret", requDetect.getApi_secret());
        multiValueMap.add("image_url", requDetect.getImage_url());
        multiValueMap.add("return_landmark", requDetect.getReturn_landmark());
        multiValueMap.add("return_attributes", requDetect.getReturn_attributes());
        ResponseEntity<String> post = RestTemplateUtils.post(FaceUrl.url_face_detection, multiValueMap, String.class);
        System.out.println(post.getBody());
        RespDetect respDetect = JSONObject.parseObject(post.getBody(), RespDetect.class);
        System.out.println(requDetect);


    }

    @Test
    public void deleteAllFaceset() {
        boolean b = facesetApiService.deleteAllFaceset();
        System.out.println(b);


    }
    @Test
    public void asynAddFace() {
        RespAddFace respAsynAddFace = restFaceApi.addFace(new RequAddFace(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), "face_90.0_-45.0", "515fb92c88756cb1cee08e5ae3bc67b2"));
        System.out.println(respAsynAddFace);

    }

    @Test
    public void createFaceset() {
        RespCreateFaceset faceset = restFaceApi.createFaceset(new RequCreateFaceset(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), "face_11.25_84.375", "face_11.25_84.375", "face_11.25_84.375", null, null, 1));
        System.out.println(faceset);

    }
    @Test
    public void deleteFaceset() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",faceSecretConfig.getApiKey());
        multiValueMap.add("api_secret",faceSecretConfig.getApiSecret());
        multiValueMap.add("outer_id", "face_-90.0_45.0");
        multiValueMap.add("check_empty", String.valueOf(0));
        try {
            restFaceApi.deleteFaceset( new RequDeleteFaceset(faceSecretConfig.getApiKey(),faceSecretConfig.getApiSecret(),"aaa",0));

        } catch (HttpClientErrorException e) {
            throw new FaceApiException("抛出异常!");
        }
        System.out.println("-----------aaaaaaaaaaaa------");


    }


}
