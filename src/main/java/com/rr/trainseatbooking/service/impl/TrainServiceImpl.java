package com.rr.trainseatbooking.service.impl;

import com.rr.trainseatbooking.dto.request.TrainRequest;
import com.rr.trainseatbooking.entity.Train;
import com.rr.trainseatbooking.exception.ResourceNotFoundException;
import com.rr.trainseatbooking.repository.TrainRepository;
import com.rr.trainseatbooking.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    public Train createTrain(TrainRequest request) {

        if (trainRepository.existsByTrainNumber(request.getTrainNumber())) {
            throw new RuntimeException("Train number already exists.");
        }

        Train train = Train.builder()
                .trainNumber(request.getTrainNumber())
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .build();

        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Train not found with id: " + id));
    }

    @Override
    public Train updateTrain(Long id, TrainRequest request) {

        Train train = getTrainById(id);

        train.setTrainNumber(request.getTrainNumber());
        train.setName(request.getName());
        train.setDescription(request.getDescription());
        train.setActive(request.getActive());

        return trainRepository.save(train);
    }

    @Override
    public void deleteTrain(Long id) {

        Train train = getTrainById(id);

        trainRepository.delete(train);
    }
}
