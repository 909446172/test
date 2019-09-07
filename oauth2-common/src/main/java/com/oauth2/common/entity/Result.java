package com.oauth2.common.entity;

import com.oauth2.common.constant.SM;
import lombok.Data;

import java.io.Serializable;

@Data
public class  Result  implements Serializable {
    private static final long serialVersionUID = 1L;
    private SM sm;
    private Object info;

}
