package demo.face.plus.faceplus.face;

import demo.face.plus.faceplus.face.entity.*;

import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/18 19:44
 * @Version 1.0
 */
public interface RestFaceApi {
    RespCreateFaceset createFaceset(RequCreateFaceset respCreateFaceset);

    RespAddFace addFace(RequAddFace requAddFace);

    RespRemoveFace removeFace(RequRemoveFace requRemoveFace);

    RespDeleteFaceset deleteFaceset(RequDeleteFaceset requDeleteFaceset);

    RespDetect detect(RequDetect requDetect);

    void checkRequest(String url, Map<String, String> requestParam);



}
