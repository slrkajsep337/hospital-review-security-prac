package com.hospital.review.hospitalreviewjpa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;


@Getter
@AllArgsConstructor
public class HospitalReviewException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    //crtl+o -> toString override
    @Override
    public String toString() {
        if(message == null) return errorCode.getMessage();
        return String.format("%s. %s", errorCode.getMessage(), message);
    }


}
