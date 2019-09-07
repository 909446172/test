package com.address.customIp.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code ;
    private com.address.customIp.resource.Data data;

}
