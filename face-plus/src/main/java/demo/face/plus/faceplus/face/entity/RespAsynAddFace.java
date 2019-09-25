package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/19 8:48
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespAsynAddFace {
    private String requestId;
    private String taskId;
    private Integer timeUsed;
    private String errorMessage;

}
