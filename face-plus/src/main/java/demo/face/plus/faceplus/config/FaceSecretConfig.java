package demo.face.plus.faceplus.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author zyy
 * @Date 2019/9/18 21:07
 * @Version 1.0
 */
@Component
@ConfigurationProperties("face")
@Data
public class FaceSecretConfig {

    private  String apiKey;
    private String apiSecret;
    private String facePrefix;
    private String facesetSuperiorLimit;
    private String interval;
    private String badNumber;






}
