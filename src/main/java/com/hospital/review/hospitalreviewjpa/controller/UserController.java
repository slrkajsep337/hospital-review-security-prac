package com.hospital.review.hospitalreviewjpa.controller;


import com.hospital.review.hospitalreviewjpa.domain.Response;
import com.hospital.review.hospitalreviewjpa.dto.*;
import com.hospital.review.hospitalreviewjpa.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 가 합쳐진 형태로 Json 형태로 객체 데이터를 반환한다.
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    //서비스<--userdto--  [컨트롤러] --리스폰스-->웹

    private final UserService us;

    @PostMapping("/join")
    //response entity가 아닌 response 타입으로 반환
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = us.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmail()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = us.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }



}
