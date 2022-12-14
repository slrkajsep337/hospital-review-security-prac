package com.hospital.review.hospitalreviewjpa.domain;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;
    private String password;
    private String emailAddress;

    //userRole 목록 enum으로 만들어주기
    @Enumerated(EnumType.STRING)
    private UserRole role;

}


