package com.hospital.review.hospitalreviewjpa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    //token을 만들어주는 클래스

    //key로 token을 열어서 그 내용을 꺼내오는 메소드
    private static Claims extractClaims(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    //token에서 name만 가져오는 메소드
    public static String getUserName(String token, String key) {
        return extractClaims(token, key).get("userName").toString();
    }

    //token이 만료 되었는지, 안되었는지 판별
    public static boolean isExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String createToken(String userName, String key, long expireTime) {
        //토큰을 만드는데 필요한 3가지 정보 -> userName(토큰의 바디,claims에들어감), key(인증용), expireTime(유효기간)
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
