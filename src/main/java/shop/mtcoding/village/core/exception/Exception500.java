package shop.mtcoding.village.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.village.dto.ResponseDTO;

@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        return new ResponseDTO<>(-1,500, "serverError", getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}