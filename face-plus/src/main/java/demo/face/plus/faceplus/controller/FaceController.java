package demo.face.plus.faceplus.controller;

import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.entity.*;
import demo.face.plus.faceplus.exception.FaceApiException;
import demo.face.plus.faceplus.face.RestFaceApi;
import demo.face.plus.faceplus.face.entity.Faces;
import demo.face.plus.faceplus.face.entity.RequDetect;
import demo.face.plus.faceplus.face.entity.RespDetect;
import demo.face.plus.faceplus.repository.UserFaceInfoRepository;
import demo.face.plus.faceplus.service.FacesetInfoService;
import demo.face.plus.faceplus.service.UserFaceInfoService;
import demo.face.plus.faceplus.service.UserInfoService;
import demo.face.plus.faceplus.vo.FaceEnableRequest;
import demo.face.plus.faceplus.vo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @Author zyy
 * @Date 2019/9/19 16:37
 * @Version 1.0
 */
@Api(tags = "face api")
@RestController
@CrossOrigin
public class FaceController {

    @Autowired
    FacesetInfoService facesetApiService;

    @Autowired
    RestFaceApi restFaceApi;

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    UserFaceInfoRepository userFaceInfoRepository;

    @ApiOperation("face 激活接口")
    @PostMapping(value = "faces", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserFaceInfo> createFace(@Valid @RequestBody @ApiParam("face激活请求的参数") FaceEnableRequest faceEnableRequest) {
        UserFaceInfo userFaceInfo = null;
        String img = faceEnableRequest.getImg();
        String userid = faceEnableRequest.getUserId();
        Location location = faceEnableRequest.getLocation();
        RespDetect detect = restFaceApi.detect(new RequDetect(faceSecretConfig.getApiKey(), faceSecretConfig.getApiSecret(), img, 0));
        if (ObjectUtils.isEmpty(detect)) {
            throw new FaceApiException("requDetect api failed !!!");
        }
        if (detect.getFaces().length > 0) {
            UserInfo userInfo = userInfoService.repairFaceset(userid, location);  //效验用户,如果没有自动添加一条 ;
            UserFaceInfo userFaceInfo1 = facesetApiService.verifiesFaceset(userInfo);// 根据用户信息判断 faceset是否可用, 返回一个可用的faceset;
            List<FaceInfo> faceInfoList = new ArrayList<>();
            for (Faces f : detect.getFaces()) {
                //对detect的faceinfo 转化为 自定义的faceinfo 信息
                FaceInfo faceInfo = new FaceInfo(img, f.getAttributes().getAge().getValue(), f.getAttributes().getGender().getValue(), f.getAttributes().getBeauty().getValue(), 1, f.getFaceToken());
                faceInfoList.add(faceInfo);
            }
            System.out.println("-----------------------"+userFaceInfoService.getClass());
            userFaceInfo = userFaceInfoService.addFaceInfo(userFaceInfo1.getId(), faceInfoList);
        }
        return new ResponseEntity<>(HttpStatus.OK,userFaceInfo);
    }

    @ApiOperation("face 匹配人脸")
    @PostMapping("mateFace")
    public ResponseEntity<Object> mateFace(@Param("location")@ApiParam("位置信息")  Location location ,@ApiParam("图片路径")@Param("imgurl") String imgurl) {
        List<UserFaceInfo> honeyFaceInfoOfNear = userFaceInfoService.findHoneyFaceInfoOfNear(location, 40, imgurl);

        return null;
    }




}
