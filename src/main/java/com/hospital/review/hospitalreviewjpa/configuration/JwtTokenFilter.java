package com.hospital.review.hospitalreviewjpa.configuration;

import com.hospital.review.hospitalreviewjpa.service.UserService;


import com.hospital.review.hospitalreviewjpa.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter { //입장할 때 마다 티켓을 보여주는 방식

    private final UserService userService;
    private final String secretKey;
    // token을 여는 아이 (필수)

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //권한을 주거나 안주기, 개찰구 역할. 현재는 모두 닫혀있는 상태

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //authrization의 header 꺼내기
        log.info("authorizationHeader:{}", authorizationHeader);

        //token이 null이면 문을 막아줘야함 -> try-catch로 처리
//        try {
//            String token = authorizationHeader.split(" ")[1];
//        } catch (Exception e) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        //token이 null이면 문을 막아줘야함 -> if로 처리, 이게 조금 더 나은방식
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                                                                    //jwt 토큰은 항상 "Bearer " + ~~ 형식으로 생성된다
            filterChain.doFilter(request, response); //권한부여를 하지말고 다음체인으로 넘어가라
            return;
        }

        // token 분리
        String token;
        try {
            //0번째 idx 값은 "Bearer"이고 1번째 idx 값이 token이다.
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
//            log.error("token 추출에 실패했습니다.");
            log.info("token 추출에 실패했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token이 만료 되었는지 check
        if(JwtUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        //token에서 name만 가져오기
        String userName = JwtUtil.getUserName(token, secretKey);
        log.info("UserName : {} ", userName);

        //문 열어주기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("",
                null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
        filterChain.doFilter(request, response);

    }
}
