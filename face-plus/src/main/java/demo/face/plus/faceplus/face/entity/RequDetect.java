package demo.face.plus.faceplus.face.entity;

import lombok.*;

/**
 * @Author zyy
 * @Date 2019/9/19 9:23
 * @Version 1.0
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RequDetect {
    @NonNull
    private String api_key;
    @NonNull
    private String api_secret;
    @NonNull
    private String image_url;
    @NonNull
    private Integer return_landmark;
    private String return_attributes = "age,gender,beauty";

}
