package shop.mtcoding.village.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.village.dto.ResponseDTO;

// 리소스 없음
@Getter
public class Exception404 extends RuntimeException{
    public Exception404(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        ResponseDTO<String> responseDto = new ResponseDTO<>();
        responseDto.fail(-1, 404,"notFound", getMessage());
        return responseDto;
    }

    public HttpStatus status(){
        return HttpStatus.NOT_FOUND;
    }
}
