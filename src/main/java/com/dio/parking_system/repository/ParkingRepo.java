package com.dio.parking_system.repository;

import com.dio.parking_system.domain.ParkingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepo extends JpaRepository<ParkingHistory, Long> {
}

