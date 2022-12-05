package com.hospital.review.hospitalreviewjpa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReviewCreateRequest {
    private String hospitalId;
    private String title;
    private String content;
    private String userName;
}
