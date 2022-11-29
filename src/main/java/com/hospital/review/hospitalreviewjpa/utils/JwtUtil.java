package com.hospital.review.hospitalreviewjpa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    //                    토큰을 만드는데 필요한 3가지 정보 -> userName(토큰의 바디,claims에들어감), key(인증용), expireTime(유효기간)
    public static String createToken(String userName, String key, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("userName",userName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
