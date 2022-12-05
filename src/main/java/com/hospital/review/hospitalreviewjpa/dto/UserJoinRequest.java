package com.hospital.review.hospitalreviewjpa.dto;


import com.hospital.review.hospitalreviewjpa.domain.User;
import com.hospital.review.hospitalreviewjpa.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserJoinRequest { //회원가입 때 요청받을 정보
    private String userName;
    private String password;
    private String email;


    //request정보 -> 엔티티로 만들어주기
    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password) //password를 그대로 넣는것이 아니라, 복호화를 해서 넣음
                .emailAddress(this.email)
                .role(UserRole.USER) //role 추가
                .build();
    }
}
