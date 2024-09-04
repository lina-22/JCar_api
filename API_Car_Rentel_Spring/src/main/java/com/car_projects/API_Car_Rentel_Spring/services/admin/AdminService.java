package com.car_projects.API_Car_Rentel_Spring.services.admin;

import com.car_projects.API_Car_Rentel_Spring.dto.CarDto;

import java.io.IOException;

public interface AdminService {

     boolean postCar(CarDto carDto) throws IOException;
}
