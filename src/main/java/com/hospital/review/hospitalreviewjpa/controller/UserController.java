package com.hospital.review.hospitalreviewjpa.controller;


import com.hospital.review.hospitalreviewjpa.domain.Response;
import com.hospital.review.hospitalreviewjpa.dto.UserDto;
import com.hospital.review.hospitalreviewjpa.dto.UserJoinRequest;
import com.hospital.review.hospitalreviewjpa.dto.UserJoinResponse;
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

    private final UserService us;

    @PostMapping
    //response entity가 아닌 response 타입으로 반환
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = us.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmail()));
    }

}
