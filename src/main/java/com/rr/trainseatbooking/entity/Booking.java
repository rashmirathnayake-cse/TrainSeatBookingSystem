package com.rr.trainseatbooking.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {


    private String passengerName;


    private String phone;



    private Integer originSequence;


    private Integer destinationSequence;


    private Double fare;



    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;



    @ManyToOne(fetch = FetchType.LAZY)
    private Seat seat;



    @ManyToOne(fetch = FetchType.LAZY)
    private Station originStation;



    @ManyToOne(fetch = FetchType.LAZY)
    private Station destinationStation;


}