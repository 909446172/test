package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求删除faceset时的请求参数
 * @Author zyy
 * @Date 2019/9/18 20:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespDeleteFaceset {
    private String requestId;
    private String facesetToken;
    private String outerId;
    private Integer timeUsed;
    private Integer errorMessage;

}
