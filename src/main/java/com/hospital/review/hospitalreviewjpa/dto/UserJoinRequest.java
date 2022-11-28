package com.hospital.review.hospitalreviewjpa.dto;


import com.hospital.review.hospitalreviewjpa.domain.User;
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
    public User toEntity() {
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .emailAddress(this.email)
                .build();
    }
}
