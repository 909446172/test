package com.oauth2.common.entity;

import com.oauth2.common.constant.SM;
import lombok.Data;

@Data
public class Result {
    private SM sm;
    private Object info;

}
