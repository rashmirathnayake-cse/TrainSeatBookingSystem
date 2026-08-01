package com.rr.trainseatbooking.repository;

import com.rr.trainseatbooking.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository
        extends JpaRepository<Train, Long> {

    boolean existsByTrainNumber(String trainNumber);

}