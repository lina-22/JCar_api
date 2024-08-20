package com.car_projects.API_Car_Rentel_Spring.repository;

import com.car_projects.API_Car_Rentel_Spring.entity.User;
import com.car_projects.API_Car_Rentel_Spring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}
