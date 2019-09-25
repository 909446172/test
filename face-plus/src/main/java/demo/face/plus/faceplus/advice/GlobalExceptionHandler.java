package demo.face.plus.faceplus.advice;

import demo.face.plus.faceplus.exception.FaceApiException;
import demo.face.plus.faceplus.exception.entity.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse IllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument", e);
        return new ExceptionResponse(BAD_REQUEST, e);
    }
    @ExceptionHandler(FaceApiException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse faceApiException(FaceApiException e) {
        log.error("FaceApiException", e);
        return new ExceptionResponse(BAD_REQUEST, e);
    }


}
