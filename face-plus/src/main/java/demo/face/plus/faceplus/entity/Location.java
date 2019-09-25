package demo.face.plus.faceplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Author zyy
 * @Date 2019/9/18 11:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Location implements Serializable {
    private static final long serialVersionUID = 4812281793511940062L;
    @Min(-180)
    @Max(180)
    @Field("longitude")
     private double longitude;
    @Min(-90)
    @Max(90)
    @Field("latitude")
     private double latitude;
}
