package demo.face.plus.faceplus.vo;

import demo.face.plus.faceplus.entity.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @Author zyy
 * @Date 2019/9/19 16:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("face 激活请求参数")
public class FaceEnableRequest {

    @ApiModelProperty("图片url")
    private String img;

    @ApiModelProperty("用户id")
    private String  userId;

    @ApiModelProperty("位置信息")
    @Valid
    private Location location;

}
