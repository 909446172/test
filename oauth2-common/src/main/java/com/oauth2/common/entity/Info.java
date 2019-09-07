package com.oauth2.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用作token信息传输;
 */
@Data
public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;


}
