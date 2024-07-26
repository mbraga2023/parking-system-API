package com.dio.parking_system.repository;

import com.dio.parking_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
