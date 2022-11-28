package com.hospital.review.hospitalreviewjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
}