package demo.face.plus.faceplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Author zyy
 * @Date 2019/9/18 16:55
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaLimit implements Serializable {

    private static final long serialVersionUID = 4240257428484672384L;
    @Min(0)
    @Max(360)
    private Double  longitude;
    @Min(0)
    @Max(180)
    private Double  latitude;
    @Min(1)
    @Max(4)
    private Integer position;

}
