package com.andrew.campy.repository;

import com.andrew.campy.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByScheduledDate(LocalDate date);

}