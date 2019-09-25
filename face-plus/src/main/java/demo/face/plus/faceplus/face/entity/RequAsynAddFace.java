package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/19 8:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequAsynAddFace {
    private String apiKey;
    private String apiSecret;
    private String outerId;
    private String faceTokens;

}
