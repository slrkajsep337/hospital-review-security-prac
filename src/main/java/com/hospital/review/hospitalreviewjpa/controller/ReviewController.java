package com.hospital.review.hospitalreviewjpa.controller;

import com.hospital.review.hospitalreviewjpa.dto.ReviewCreateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest dto) {
        return "리뷰 등록에 성공했습니다";
    }

}
