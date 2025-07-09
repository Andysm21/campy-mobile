package com.andrew.campy.repository;

import com.andrew.campy.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampRepository extends JpaRepository<Camp, Long> {
}
