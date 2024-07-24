package com.car_projects.API_Car_Rentel_Spring.services.auth;


import com.car_projects.API_Car_Rentel_Spring.dto.Signuprequest;
import com.car_projects.API_Car_Rentel_Spring.dto.UserDto;

public interface AuthService{

    UserDto createCustomer(Signuprequest signuprequest);

    boolean hasCustomerWithEmail(String email);
}
