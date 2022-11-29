package com.hospital.review.hospitalreviewjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginRequest {
    private String userName;
    private String password;
}


