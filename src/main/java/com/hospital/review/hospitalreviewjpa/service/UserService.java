package com.hospital.review.hospitalreviewjpa.service;

import com.hospital.review.hospitalreviewjpa.domain.User;
import com.hospital.review.hospitalreviewjpa.dto.UserDto;
import com.hospital.review.hospitalreviewjpa.dto.UserJoinRequest;
import com.hospital.review.hospitalreviewjpa.exception.ErrorCode;
import com.hospital.review.hospitalreviewjpa.exception.HospitalReviewException;
import com.hospital.review.hospitalreviewjpa.repository.UserRepository;
import com.hospital.review.hospitalreviewjpa.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    //비즈니스 로직 - 회원가입

    private final UserRepository ur;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private  String secretKey;
    private long expireTime = 1000 * 60 * 60; //1시간


    public UserDto join(UserJoinRequest request) {
        //userName(id) 중복 체크 -> 중복이면 회원가입 불가(exception 발생)
//        ur.findByUserName(request.getUserName())
//                .orElseThrow(() -> new RuntimeException("해당 유저 이름이 중복됩니다."));

        ur.findByUserName(request.getUserName())
                //ifPresent : user가 있으면 -> ~~ 이렇게 처리를 해줘라/ orElseThrow랑 반대
                .ifPresent(user -> {
                    throw new RuntimeException("해당 유저 이름이 중복됩니다.");
                });

        // 회원가입 .save()
        User savedUser = ur.save(request.toEntity(encoder.encode(request.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();

    }

    //토큰은 string 타입이므로 string으로 return
    public String login(String userName, String password) {

        // userName있는지 여부 확인, userName이 없으면 NOT_FOUND에러 발생시키기
        User user = ur.findByUserName(userName)
                //orElseThrow:  없는 경우에 -> ~~ 이렇게 처리를 해줘라
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)));

        // userName이 있다면 그 다음에는 password가 일치 하는지 여부 확인, 비밀번호가 틀리다면
//        if(password!=user.getPassword()) {
//            throw new HospitalReviewException(ErrorCode.INVALID_PASSWORD,"비밀번호가 틀렸습니다");
//        }
        if(!encoder.matches(password, user.getPassword())) {
            throw new HospitalReviewException(ErrorCode.INVALID_PASSWORD,"비밀번호가 틀렸습니다");
        };

        // 두가지 확인중 예외 안났으면 Token발행
        return JwtUtil.createToken(userName, secretKey, expireTime);
    }


}


