package com.example.task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="address")
    private String address;
    @Column(name="town_name")
    private String townName;
    @Column(name="time_zone")
    private String timeZone;

}
