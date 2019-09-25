package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/19 18:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceRectangle {
    private Integer top;
    private Integer left;
    private Integer width;
    private Integer height;


}
