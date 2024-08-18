package com.car_projects.API_Car_Rentel_Spring.dto;

import com.car_projects.API_Car_Rentel_Spring.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;
}
