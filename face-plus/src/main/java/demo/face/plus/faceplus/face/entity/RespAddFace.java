package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/18 20:10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespAddFace {
    private String requestId;
    private String facesetToken;
    private String outerId;
    private Integer faceAdded;
    private Integer faceCount;
    private FailureDetail[] failureDetail;
    private Integer timeUsed;
    private String errorMessage;

}
