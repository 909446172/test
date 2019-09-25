package com.example111.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zyy
 * @Date 2019/9/23 17:02
 * @Version 1.0
 */
@Service
public class UserFaceInfoServiceImpl {

    @Autowired
    FacesetApiServiceImpl facesetApiService;

}
