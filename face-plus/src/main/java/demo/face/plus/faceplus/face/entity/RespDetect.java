package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/19 18:22
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespDetect {
    private String requestId;
    private Faces[] faces;
    private String imageId;
    private String timeUsed;
    private String errorMessage;

}
