package com.hospital.review.hospitalreviewjpa.exception;


import com.hospital.review.hospitalreviewjpa.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    // <?> : 아무 타입이나 받을 수 있다
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        // RuntimeException이 나면 Controller대신 이곳에서 리턴을 해줍니다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));
        // INTERNAL_SERVER_ERROR를 리턴하고 ResponseBody에 e.getMessage()를 추가해서 보냅니다.
    }

    @ExceptionHandler(HospitalReviewException.class)
    public ResponseEntity<?> hospitalReviewExceptionHandler(HospitalReviewException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                //Enum으로 선언한 ErrorCode를 리턴하기 위해 getErrorCode()사용
                .body(Response.error(e.getErrorCode().getMessage()));
    }
}
