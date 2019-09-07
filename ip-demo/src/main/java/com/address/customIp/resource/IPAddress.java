package com.address.customIp.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class IPAddress {
    private String ip1;
    private String ip2;
    private Long  ip1Code;
    private Long  ip2Code;
    private String country;
    private String region;

}
