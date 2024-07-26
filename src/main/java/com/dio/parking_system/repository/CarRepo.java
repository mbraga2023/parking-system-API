package com.dio.parking_system.repository;

import com.dio.parking_system.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}
