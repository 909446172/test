package com.address.customIp.resource;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import lombok.Data;

import java.io.Serializable;

@Data
public class RequestAttr  implements Serializable {


    private static final long serialVersionUID = 1L;
    private String key;
    private String ip;
    private String output;
}
