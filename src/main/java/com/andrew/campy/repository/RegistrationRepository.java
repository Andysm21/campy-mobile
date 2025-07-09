package com.andrew.campy.repository;


import com.andrew.campy.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByCampId(Long campId);
}