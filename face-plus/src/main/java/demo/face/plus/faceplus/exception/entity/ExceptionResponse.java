package demo.face.plus.faceplus.exception.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;


@Data
public class ExceptionResponse {

    @ApiModelProperty("时间")
    private Date timestamp;

    @ApiModelProperty(position = 1, value = "HTTP状态码")
    private Integer status;

    @ApiModelProperty(position = 2, value = "HTTP原因短语")
    private String error;

    @ApiModelProperty(position = 3, value = "消息")
    private String message;

    public ExceptionResponse(HttpStatus httpStatus, Exception e) {
        timestamp = new Date();
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        message = e.getMessage();
    }
}
