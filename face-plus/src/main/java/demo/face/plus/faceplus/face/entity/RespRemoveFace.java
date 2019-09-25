package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建faceset Api的响应参数
 * @Author zyy
 * @Date 2019/9/18 19:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespRemoveFace {
    private String requestId;
    private String facesetToken;
    private String outerId;
    private Integer faceRemoved;
    private Integer faceCount;
    private FailureDetail[] failureDetail;
    private Integer timeUsed;
    private String errorMessage;



}
