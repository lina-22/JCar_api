package com.car_projects.API_Car_Rentel_Spring.dto;

import com.car_projects.API_Car_Rentel_Spring.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
