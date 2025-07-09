package com.andrew.campy.service;


import com.andrew.campy.dto.GameDTO;
import com.andrew.campy.dto.UnitResultDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface GameService {
    Long createGame(GameDTO dto);
    void saveResults(Long gameId, List<UnitResultDTO> results);
    Map<String, Integer> calculateTotalScores();
    Map<String, Integer> calculateScoresByDate(LocalDate date);

}