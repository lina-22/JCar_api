package com.car_projects.API_Car_Rentel_Spring.controller;

import com.car_projects.API_Car_Rentel_Spring.dto.Signuprequest;
import com.car_projects.API_Car_Rentel_Spring.dto.UserDto;
import com.car_projects.API_Car_Rentel_Spring.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody Signuprequest signuprequest){
        if(authservice.hasCustomerWithEmail(signuprequest.getEmail()))
            return  new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authservice.createCustomer(signuprequest);

        if(createdCustomerDto==null) return new ResponseEntity<>("Customer not created, Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

}
