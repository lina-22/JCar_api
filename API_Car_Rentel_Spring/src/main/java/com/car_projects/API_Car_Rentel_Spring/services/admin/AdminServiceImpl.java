package com.car_projects.API_Car_Rentel_Spring.services.admin;

import com.car_projects.API_Car_Rentel_Spring.dto.CarDto;
import com.car_projects.API_Car_Rentel_Spring.entity.Car;
import com.car_projects.API_Car_Rentel_Spring.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CarRepository carRepository;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);  // Add a logger

    @Override
    public boolean postCar(CarDto carDto) throws IOException {

      try{
          Car car = new Car();

          car.setName(carDto.getName());  // Check if this is still null
//          car.setImage(carDto.getImage().getBytes());

          logger.debug("Car to be saved - Name: {}", car.getName());  // Log the Car object before saving

          car.setBrand(carDto.getBrand());
          car.setColor(carDto.getColor());
          car.setType(carDto.getType());
          car.setTransmission(carDto.getTransmission());
          car.setDescription(carDto.getDescription());
          car.setPrice(carDto.getPrice());
          car.setYear(carDto.getYear());
//          car.setImage(carDto.getImage().getBytes());
          logger.debug("Saving Car entity: {}", car);  // Log the Car object before saving

          carRepository.save(car);
          return true;
      }catch (Exception e){
          logger.error("Error while saving car: ", e);  // Log the error in case of exception
          e.printStackTrace(); // Logging the exception for debugging
          return false;
      }
    }
}
