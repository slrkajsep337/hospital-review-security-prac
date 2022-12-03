package com.hospital.review.hospitalreviewjpa.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    //에러코드를 포함시켜 리턴하기 위함 모든 Response는 이 Response객체로 감싸서 Return
    // -> dto들이 모두 resultCode를 포함해서 반환할 수 있도록, 감싸주는(한번 거쳐가는)클래스.
    //dto별 타입이 다를테니까 지정해주기 위해서 제네릭으로 선언

    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    //앞에 <T>는 제네릭 메소드 생성 규칙 (public, static 같은 것)
    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
}

