package com.car_projects.API_Car_Rentel_Spring.services.auth;

import com.car_projects.API_Car_Rentel_Spring.dto.Signuprequest;
import com.car_projects.API_Car_Rentel_Spring.dto.UserDto;
import com.car_projects.API_Car_Rentel_Spring.entity.User;
import com.car_projects.API_Car_Rentel_Spring.enums.UserRole;
import com.car_projects.API_Car_Rentel_Spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService, CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Inject password encoder
    @Override
    public void run(String... args) throws Exception {
        createAdminAccount();  // This will be executed after the application starts
    }

    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null){
            User newAdminAccount = new User();
            newAdminAccount.setName("ADMIN");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(passwordEncoder.encode("admin"));  // Use the injected password encoder
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created successfulley");
        }
    }
    @Override
    public UserDto createCustomer(Signuprequest signuprequest) {

        User user = new User();
        user.setName(signuprequest.getName());
        user.setEmail(signuprequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
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
