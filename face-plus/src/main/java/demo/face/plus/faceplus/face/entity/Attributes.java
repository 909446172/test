package demo.face.plus.faceplus.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author zyy
 * @Date 2019/9/19 18:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    @Min(1)
    @Max(200)
    @NonNull
    private Age age;

    @NonNull
    private Gender gender;

    @NonNull
    private Beauty beauty;



}
