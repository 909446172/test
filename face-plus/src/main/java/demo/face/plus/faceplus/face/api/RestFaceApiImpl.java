package demo.face.plus.faceplus.face.api;

import com.alibaba.fastjson.JSONObject;
import demo.face.plus.faceplus.async.FaceAgainExecuteForBadRequest;
import demo.face.plus.faceplus.face.FaceUrl;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.*;
import demo.face.plus.faceplus.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/18 19:42
 * @Version 1.0
 */
@Slf4j
@Component
public class RestFaceApiImpl implements RestFaceApi {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FaceAgainExecuteForBadRequest faceAgainExecuteForBadRequest;

    @Override
    public RespCreateFaceset createFaceset(RequCreateFaceset respCreateFaceset) {
        if (ObjectUtils.isEmpty(respCreateFaceset)) {
            return null;
        }
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",respCreateFaceset.getApiKey());
        multiValueMap.add("api_secret", respCreateFaceset.getApiSecret());
        multiValueMap.add("outer_id", respCreateFaceset.getOuterId());
  //      multiValueMap.add("display_name", respCreateFaceset.getDisplayName());
 //       multiValueMap.add("face_tokens", respCreateFaceset.getFaceTokens());
//        multiValueMap.add("tags", respCreateFaceset.getTags());
//        multiValueMap.add("user_data", respCreateFaceset.getUserData());
        multiValueMap.add("force_merge", String.valueOf(respCreateFaceset.getForceMerge()));
        log.info("create faceset respCreateFaceset: "+respCreateFaceset);
   //     ResponseEntity<String> post = restTemplate.postForEntity(FaceUrl.url_faceset_create, multiValueMap, String.class);
        String s = HttpClientUtils.doPost(FaceUrl.url_faceset_create, multiValueMap.toSingleValueMap());
        if (StringUtils.isEmpty(s)) {
            checkRequest(FaceUrl.url_faceset_create, multiValueMap.toSingleValueMap());
        }
        RespCreateFaceset respCreateFaceset1 = JSONObject.parseObject(s, RespCreateFaceset.class);

        if (ObjectUtils.isEmpty(respCreateFaceset1)) {
            return null;
        }
        return respCreateFaceset1;
    }

    @Override
    public RespRemoveFace removeFace(RequRemoveFace requRemoveFace) {
        if (ObjectUtils.isEmpty(requRemoveFace)) {
            return null;
        }
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",requRemoveFace.getApiKey());
        multiValueMap.add("api_secret", requRemoveFace.getApiSecret());
        multiValueMap.add("outer_id", requRemoveFace.getOuterId());
        multiValueMap.add("face_tokens", requRemoveFace.getFaceTokens());
     //   ResponseEntity<String> post =  restTemplate.postForEntity(FaceUrl.url_face_detection, multiValueMap, String.class);
        String s = HttpClientUtils.doPost(FaceUrl.url_face_detection, multiValueMap.toSingleValueMap());
        if (StringUtils.isEmpty(s)) {
            checkRequest(FaceUrl.url_face_detection, multiValueMap.toSingleValueMap());
        }
        RespRemoveFace respRemoveFace = JSONObject.parseObject(s, RespRemoveFace.class);
        if (ObjectUtils.isEmpty(respRemoveFace)) {
            return null;
        }
        return respRemoveFace;
    }

    @Override
    public RespDeleteFaceset deleteFaceset(RequDeleteFaceset requDeleteFaceset) {
        if (ObjectUtils.isEmpty(requDeleteFaceset)) {
            return null;
        }
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",requDeleteFaceset.getApiKey());
        multiValueMap.add("api_secret", requDeleteFaceset.getApiSecret());
        multiValueMap.add("outer_id", requDeleteFaceset.getOuterId());
        multiValueMap.add("check_empty", String.valueOf(requDeleteFaceset.getCheckEmpty()));
 //       ResponseEntity<String> post =  restTemplate.postForEntity(FaceUrl.url_face_delete, multiValueMap, String.class);
        String s = HttpClientUtils.doPost(FaceUrl.url_face_delete, multiValueMap.toSingleValueMap());
        if (StringUtils.isEmpty(s)) {
            checkRequest(FaceUrl.url_face_delete, multiValueMap.toSingleValueMap());
        }
        RespDeleteFaceset respDeleteFaceset = JSONObject.parseObject(s, RespDeleteFaceset.class);
        if (ObjectUtils.isEmpty(respDeleteFaceset)) {
            return null;
        }
        return respDeleteFaceset;
    }

    @Override
    public RespAddFace addFace(RequAddFace requAddFace) {
        log.info("addfaceToken ---requAddFace:"+requAddFace);
        if (ObjectUtils.isEmpty(requAddFace)) {
            return null;
        }
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",requAddFace.getApiKey());
        multiValueMap.add("api_secret", requAddFace.getApiSecret());
        multiValueMap.add("outer_id", requAddFace.getOuterId());
        multiValueMap.add("face_tokens", requAddFace.getFaceTokens());
//        ResponseEntity<String> post =  restTemplate.postForEntity(FaceUrl.url_facesetAdd_faceToken, multiValueMap, String.class);
        String s = HttpClientUtils.doPost(FaceUrl.url_facesetAdd_faceToken, multiValueMap.toSingleValueMap());
        if (StringUtils.isEmpty(s)) {
            checkRequest(FaceUrl.url_facesetAdd_faceToken, multiValueMap.toSingleValueMap());
        }
        RespAddFace respAddFace = JSONObject.parseObject(s, RespAddFace.class);
        System.out.println(respAddFace);
        if (ObjectUtils.isEmpty(respAddFace)) {
            return null;
        }
        return respAddFace;
    }

    @Override
    public RespDetect detect(RequDetect requDetect) {
        if (ObjectUtils.isEmpty(requDetect)) {
            return null;
        }
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("api_key",requDetect.getApi_key());
        multiValueMap.add("api_secret", requDetect.getApi_secret());
        multiValueMap.add("image_url", requDetect.getImage_url());
        multiValueMap.add("return_landmark",String.valueOf(requDetect.getReturn_landmark()));
        multiValueMap.add("return_attributes", requDetect.getReturn_attributes());
        //ResponseEntity<String> post =  restTemplate.postForEntity(FaceUrl.url_face_detection, multiValueMap, String.class);
        String s = HttpClientUtils.doPost(FaceUrl.url_face_detection, multiValueMap.toSingleValueMap());
        if (StringUtils.isEmpty(s)) {
        checkRequest(FaceUrl.url_face_detection, multiValueMap.toSingleValueMap());
        }
        log.info(s);
        RespDetect respDetect = JSONObject.parseObject(s, RespDetect.class);
        System.out.println(requDetect);
        if (ObjectUtils.isEmpty(respDetect)) {
            return null;
        }
        return respDetect;
    }

    /**
     *  验证发送的请求 , 有时候会因为请求过多而导致请求失败 , 对于失败的请求重新发送5次
     * @param url
     * @param requestParam
     * @return
     */
    @Override
    public void checkRequest(String url, Map<String, String> requestParam) {
       String s = HttpClientUtils.doPost(FaceUrl.url_face_detection, requestParam);
        if (StringUtils.isEmpty(s)) {
            faceAgainExecuteForBadRequest.FaceApiRollBack(url, requestParam);
        }
    }


}
