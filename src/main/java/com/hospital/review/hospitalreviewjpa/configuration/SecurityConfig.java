package com.hospital.review.hospitalreviewjpa.configuration;

import com.hospital.review.hospitalreviewjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    //앞에서 잘 작동하던 join이 안되는 문제를 해결하기 위해 생성

    private final UserService us;

    @Value("${jwt.token.secret}")
    private String secretkey;

    //security 설정 ?
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/v1/users/join", "/api/v1/users/login").permitAll() // join, login은 언제나 가능
                //접근 요청 막기(review는 인증된 user만 post할 수 있도록), 위에서 ^ 허용해줬기 때문에 join과 login은 가능함 -> 문만들어주기
                // antMatchers 메서드의 인자와 매칭되는 경우, 인증점검을 한다
                .antMatchers(HttpMethod.POST, "/api/v1/**").authenticated() //꼭 permitAll 해주고 넣어줘야함, 안그러면 다막힘
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용하는 경우 씀
                .and()
                .addFilterBefore(new JwtTokenFilter(us, secretkey), UsernamePasswordAuthenticationFilter.class)
                //UserNamePasswordAuthenticationFilter적용하기 전에 JWTTokenFilter를 적용 하라는 뜻 입니다.
                .build();
    }
}
