package com.car_projects.API_Car_Rentel_Spring.services.auth;

import com.car_projects.API_Car_Rentel_Spring.dto.Signuprequest;
import com.car_projects.API_Car_Rentel_Spring.dto.UserDto;
import com.car_projects.API_Car_Rentel_Spring.entity.User;
import com.car_projects.API_Car_Rentel_Spring.enums.UserRole;
import com.car_projects.API_Car_Rentel_Spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    @Override
    public UserDto createCustomer(Signuprequest signuprequest) {

        User user = new User();
        user.setName(signuprequest.getName());
        user.setEmail(signuprequest.getEmail());
        user.setPassword(signuprequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setName(createdUser.getName());
        userDto.setEmail(createdUser.getEmail());

        userDto.setUserRole(createdUser.getUserRole());

        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {

        return userRepository.findFirstByEmail(email).isPresent();
    }
}
