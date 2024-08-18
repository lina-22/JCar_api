package com.car_projects.API_Car_Rentel_Spring.controller;

import com.car_projects.API_Car_Rentel_Spring.dto.AuthenticationRequest;
import com.car_projects.API_Car_Rentel_Spring.dto.AuthenticationResponse;
import com.car_projects.API_Car_Rentel_Spring.dto.Signuprequest;
import com.car_projects.API_Car_Rentel_Spring.dto.UserDto;
import com.car_projects.API_Car_Rentel_Spring.entity.User;
import com.car_projects.API_Car_Rentel_Spring.repository.UserRepository;
import com.car_projects.API_Car_Rentel_Spring.services.auth.AuthService;
import com.car_projects.API_Car_Rentel_Spring.services.jwt.UserService;
import com.car_projects.API_Car_Rentel_Spring.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody Signuprequest signuprequest){
        if(authservice.hasCustomerWithEmail(signuprequest.getEmail()))
            return  new ResponseEntity<>("Customer already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authservice.createCustomer(signuprequest);

        if(createdCustomerDto==null) return new ResponseEntity<>("Customer not created, Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.getEmail() == null || authenticationRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (authenticationRequest.getPassword() == null || authenticationRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(user.getId());
            authenticationResponse.setUserRole(user.getUserRole());
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return ResponseEntity.ok(authenticationResponse);
    }
}