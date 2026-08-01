package com.rr.trainseatbooking.service;

import com.rr.trainseatbooking.dto.request.TrainRequest;
import com.rr.trainseatbooking.entity.Train;

import java.util.List;

public interface TrainService {

    Train createTrain(TrainRequest request);

    List<Train> getAllTrains();

    Train getTrainById(Long id);

    Train updateTrain(Long id, TrainRequest request);

    void deleteTrain(Long id);

}

