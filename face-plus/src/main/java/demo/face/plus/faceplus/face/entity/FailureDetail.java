package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/18 19:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailureDetail {
    private String reason;
    private String faceToken;

}
