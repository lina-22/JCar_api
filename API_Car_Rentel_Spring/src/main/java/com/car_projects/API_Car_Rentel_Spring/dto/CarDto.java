package com.car_projects.API_Car_Rentel_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class CarDto {
//    private Long id;
    private String name;
//    private MultipartFile image;
    private String brand;
    private String color;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;
//    private MultipartFile image;  // This field handles the file upload
//    private byte[] returnedImage;

}
