package demo.face.plus.faceplus.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @Author zyy
 * @Date 2019/9/25 9:02
 * @Version 1.0
 */
@Data
public class  ResponseEntity<T> {

    @ApiModelProperty("时间")
    private Date timestamp;

    @ApiModelProperty(position = 1, value = "HTTP状态码")
    private Integer status;

    @ApiModelProperty(position = 2,value = "实体对象")
    private  T entity;

    public ResponseEntity(HttpStatus httpStatus,T t) {
        timestamp = new Date();
        status = httpStatus.value();
        this.entity = t;
    }






}
