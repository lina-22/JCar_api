package com.car_projects.API_Car_Rentel_Spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.car_projects.API_Car_Rentel_Spring.dto.CarDto;
import com.car_projects.API_Car_Rentel_Spring.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@RequestBody CarDto carDto) throws IOException {
        logger.debug("Received CarDto: {}", carDto);  // Log the received CarDto object
        boolean success = adminService.postCar(carDto);
        if(success){
            System.out.println("new car has been created");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
