package com.car_projects.API_Car_Rentel_Spring.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
