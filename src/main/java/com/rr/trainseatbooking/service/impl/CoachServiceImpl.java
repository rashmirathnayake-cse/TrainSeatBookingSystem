package com.rr.trainseatbooking.service.impl;



import com.rr.trainseatbooking.dto.request.CoachRequest;
import com.rr.trainseatbooking.dto.response.CoachResponse;
import com.rr.trainseatbooking.entity.Coach;
import com.rr.trainseatbooking.entity.Seat;
import com.rr.trainseatbooking.entity.Train;
import com.rr.trainseatbooking.exception.ResourceNotFoundException;
import com.rr.trainseatbooking.repository.CoachRepository;
import com.rr.trainseatbooking.repository.TrainRepository;
import com.rr.trainseatbooking.service.CoachService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {


    private final CoachRepository coachRepository;

    private final TrainRepository trainRepository;



    @Override
    @Transactional
    public CoachResponse createCoach(CoachRequest request) {


        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Train not found"
                        ));



        Coach coach = Coach.builder()
                .coachNumber(request.getCoachNumber())
                .type(request.getType())
                .seatCapacity(request.getSeatCapacity())
                .train(train)
                .seats(new ArrayList<>())
                .build();



        // Generate seats automatically
        for(int i = 1; i <= request.getSeatCapacity(); i++) {

            Seat seat = Seat.builder()
                    .seatNumber(String.valueOf(i))
                    .coach(coach)
                    .build();


            coach.getSeats().add(seat);
        }

        Coach savedCoach = coachRepository.save(coach);


        return CoachResponse.builder()
                .id(savedCoach.getId())
                .coachNumber(savedCoach.getCoachNumber())
                .type(savedCoach.getType())
                .seatCapacity(savedCoach.getSeatCapacity())
                .trainId(savedCoach.getTrain().getId())
                .build();
    }



    @Override
    public List<Coach> getAllCoaches(){

        return coachRepository.findAll();

    }



    @Override
    public List<Coach> getCoachesByTrain(Long trainId){

        return coachRepository.findByTrainId(trainId);

    }



    @Override
    public Coach getCoachById(Long id){

        return coachRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Coach not found"
                        ));
    }



    @Override
    public CoachResponse updateCoach(Long id, CoachRequest request){

        Coach coach = getCoachById(id);

        coach.setCoachNumber(request.getCoachNumber());
        coach.setType(request.getType());

        Coach savedCoach = coachRepository.save(coach);


        return CoachResponse.builder()
                .id(savedCoach.getId())
                .coachNumber(savedCoach.getCoachNumber())
                .type(savedCoach.getType())
                .seatCapacity(savedCoach.getSeatCapacity())
                .trainId(savedCoach.getTrain().getId())
                .build();

    }



    @Override
    public void deleteCoach(Long id){

        Coach coach = getCoachById(id);

        coachRepository.delete(coach);

    }

}