package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加face api 的请求参数
 * @Author zyy
 * @Date 2019/9/18 20:06
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequAddFace {
    private String apiKey;
    private String apiSecret;
    private String outerId;
    private String faceTokens;


}
