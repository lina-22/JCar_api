package com.car_projects.API_Car_Rentel_Spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
//    @Column(columnDefinition = "longblob")
//    private byte[] image;

    private String brand;

    private String color;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;



}
