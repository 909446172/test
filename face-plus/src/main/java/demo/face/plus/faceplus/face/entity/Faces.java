package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyy
 * @Date 2019/9/19 18:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faces {
    private String faceToken;
    private FaceRectangle faceRectangle;
    private Object landmark;
    private Attributes attributes;


}
