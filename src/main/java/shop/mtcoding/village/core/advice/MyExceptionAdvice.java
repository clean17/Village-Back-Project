<<<<<<< HEAD
<<<<<<< HEAD
package shop.mtcoding.village.core.advice;

import org.hibernate.exception.ConstraintViolationException;
<<<<<<< HEAD
import org.springframework.dao.DataIntegrityViolationException;
=======
package shop.mtcoding.village.core.advice;

>>>>>>> c391c21 (Reservation Refactor 완료)
=======
>>>>>>> bd01da0 (Reservation save 완료)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


<<<<<<< HEAD
import shop.mtcoding.village.core.exception.*;

import io.sentry.Sentry;

import shop.mtcoding.village.dto.ResponseDTO;
=======
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.core.exception.MyConstException;

import io.sentry.Sentry;
import shop.mtcoding.village.core.exception.CustomApiException;
import shop.mtcoding.village.core.exception.CustomException;

import shop.mtcoding.village.core.exception.MyValidationException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.exception.Exception400;
>>>>>>> c391c21 (Reservation Refactor 완료)


@RestControllerAdvice
public class MyExceptionAdvice {

<<<<<<< HEAD
<<<<<<< HEAD
=======

=======
//package shop.mtcoding.village.core.advice;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//
//import shop.mtcoding.village.core.exception.Exception500;
//import shop.mtcoding.village.core.exception.MyConstException;
//
//import io.sentry.Sentry;
//import shop.mtcoding.village.core.exception.CustomApiException;
//import shop.mtcoding.village.core.exception.CustomException;
//
//import shop.mtcoding.village.core.exception.MyValidationException;
//import shop.mtcoding.village.dto.ResponseDTO;
//import shop.mtcoding.village.exception.Exception400;
//
//
//@RestControllerAdvice
//public class MyExceptionAdvice {
//
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<?> ex(Exception e){
////        Sentry.captureException(e);
////        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(500, "오류1", HttpStatus.INTERNAL_SERVER_ERROR);
////        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
////    }
//
>>>>>>> 4378292 (Place Refactor 완료)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> ex(Exception e){
//        Sentry.captureException(e);
//        String message = e.getMessage();
<<<<<<< HEAD
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(1, message, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
<<<<<<< HEAD

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        Sentry.captureException(e);
        String message = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1,400, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

>>>>>>> cb21803 (Reservation save 완료)
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, e.getMessage(), "Null");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

<<<<<<< HEAD
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            String message = cve.getSQLException().getMessage();
            if (message.contains("UK_2DLFG6WVNXBOKNKP9D1H75ICB_INDEX_2")) {
                // email이 이미 존재하는 경우에 대한 예외 처리
                ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 409, "이미 존재하는 이메일입니다.", "Null");
                return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
            } else {
                // 기타 데이터 위반 예외 처리
                ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, "데이터 위반 예외가 발생했습니다.", "Null");
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } else {
            // 기타 예외 처리
            ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
=======
=======
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, message, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
//
>>>>>>> 4378292 (Place Refactor 완료)
//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> ex400(Exception e){
//
//        Sentry.captureException(e);
//        String message = e.getMessage();
<<<<<<< HEAD
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(1, message, HttpStatus.BAD_REQUEST);
=======
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, message, HttpStatus.BAD_REQUEST);
>>>>>>> 4378292 (Place Refactor 완료)
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//
//    }
//
////    @ExceptionHandler(Exception400.class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    public ResponseEntity<?> ex401(MyValidationException e){
////        String errMsg = e.getErroMap().toString();
////        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
////        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
////    }
//
//    @ExceptionHandler(MyValidationException.class)
//    public ResponseEntity<?> error(MyValidationException e){
//        Sentry.captureException(e);
=======
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> ex(Exception e){
//        Sentry.captureException(e);
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(500, "오류1", HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        Sentry.captureException(e);
        String message = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> ex400(Exception e){

        Sentry.captureException(e);
        String message = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> ex401(MyValidationException e){
>>>>>>> c391c21 (Reservation Refactor 완료)
//        String errMsg = e.getErroMap().toString();
//        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
//        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
//    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c391c21 (Reservation Refactor 완료)

    @ExceptionHandler(MyValidationException.class)
    public ResponseEntity<?> error(MyValidationException e){
        Sentry.captureException(e);
        String errMsg = e.getErroMap().toString();
        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
<<<<<<< HEAD
        return new ResponseEntity<>(new ResponseDTO<>(-1,400,devideMsg,null), HttpStatus.BAD_REQUEST);
>>>>>>> cb21803 (Reservation save 완료)
    }


    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, "일시적인 서버 오류입니다.", "Null");
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MyConstException.class)
    public ResponseEntity<?> error(MyConstException e) {
=======
        return new ResponseEntity<>(new ResponseDTO<>(-1, 400, devideMsg,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyConstException.class)
    public ResponseEntity<?> error(MyConstException e){
>>>>>>> c391c21 (Reservation Refactor 완료)
        String detail = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, detail, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

<<<<<<< HEAD
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverError(Exception e){
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 500, e.getMessage(), "Null");
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
=======
//
//    @ExceptionHandler(MyConstException.class)
//    public ResponseEntity<?> error(MyConstException e){
//        String detail = e.getMessage();
//        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(-1, 400, detail, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<?> customException(CustomApiException e) {
//        Sentry.captureException(e);
//        return new ResponseEntity<>(new ResponseDTO<>(-1, 400, e.getMessage(), null), e.getStatus());
//    }
//}
>>>>>>> 4378292 (Place Refactor 완료)
=======
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomApiException e) {
        Sentry.captureException(e);
        return new ResponseEntity<>(new ResponseDTO<>(-1, 400, e.getMessage(), null), e.getStatus());
    }
}
>>>>>>> c391c21 (Reservation Refactor 완료)
