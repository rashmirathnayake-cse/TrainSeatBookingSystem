package com.rr.trainseatbooking.repository;

import com.rr.trainseatbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
