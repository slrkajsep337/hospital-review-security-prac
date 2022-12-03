package com.hospital.review.hospitalreviewjpa.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //enum - 들어갈 수 있는 값들을 미리 지정해두어서 다른 값은 못들어가게함. 예측한 범위 내에서만 프로그램이 작동하도록 하는 기능

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "UserName Not Found."),
    //HttpStatus -> 200, 400, 500등 ...
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is not correct") ;


    private HttpStatus status;
    private String message;


}
