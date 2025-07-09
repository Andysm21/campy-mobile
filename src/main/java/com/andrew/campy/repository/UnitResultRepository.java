package com.andrew.campy.repository;


import com.andrew.campy.entity.UnitResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitResultRepository extends JpaRepository<UnitResult, Long> {
    List<UnitResult> findByGameId(Long gameId);
    List<UnitResult> findAll();
}