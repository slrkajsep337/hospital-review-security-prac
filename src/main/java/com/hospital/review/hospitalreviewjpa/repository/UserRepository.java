package com.hospital.review.hospitalreviewjpa.repository;

import com.hospital.review.hospitalreviewjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByUserName(String userName);
}
