package com.rr.trainseatbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="trains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train extends BaseEntity {


    @Column(nullable=false)
    private String trainNumber;


    @Column(nullable=false)
    private String name;

    private String description;


    private Boolean active;


    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL
    )
    private List<Coach> coaches;

}