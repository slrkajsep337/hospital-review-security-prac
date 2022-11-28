package com.hospital.review.hospitalreviewjpa.service;

import com.hospital.review.hospitalreviewjpa.domain.User;
import com.hospital.review.hospitalreviewjpa.dto.UserDto;
import com.hospital.review.hospitalreviewjpa.dto.UserJoinRequest;
import com.hospital.review.hospitalreviewjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    //비즈니스 로직 - 회원가입

    private final UserRepository ur;

    public UserDto join(UserJoinRequest request) {
        //userName(id) 중복 체크 -> 중복이면 회원가입 불가(exception 발생)
//        ur.findByUserName(request.getUserName())
//                .orElseThrow(() -> new RuntimeException("해당 유저 이름이 중복됩니다."));

        ur.findByUserName(request.getUserName())
                //ifPresent : user가 있으면 -> ~~ 이렇게 처리를 해준다/ orElseThrow랑 반대
                .ifPresent(user -> {
                    throw new RuntimeException("해당 유저 이름이 중복됩니다.");
                });

        // 회원가입 .save()
        User savedUser = ur.save(request.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();

    }
}


