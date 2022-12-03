package com.hospital.review.hospitalreviewjpa.repository;

import com.hospital.review.hospitalreviewjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //내가 사용하고 싶은 method 선언해주기
    Optional <User> findByUserName(String userName);
}
