package com.rr.trainseatbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name="schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule extends BaseEntity {


    private LocalDate travelDate;


    private LocalTime departureTime;


    private LocalTime arrivalTime;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="train_id")
    private Train train;

}