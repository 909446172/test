package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建facesetapi的请求参数
 * @Author zyy
 * @Date 2019/9/18 19:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequCreateFaceset {
    private String apiKey;
    private String apiSecret;
    private String displayName;
    private String outerId;
    private String tags;
    private String faceTokens;
    private String userData;
    private Integer forceMerge;



}
