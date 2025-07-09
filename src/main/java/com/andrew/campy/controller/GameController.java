package com.andrew.campy.controller;


import com.andrew.campy.dto.GameDTO;
import com.andrew.campy.dto.UnitResultDTO;
import com.andrew.campy.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public Long createGame(@RequestBody GameDTO dto) {
        return gameService.createGame(dto);
    }

    @PostMapping("/{gameId}/results")
    public void submitResults(@PathVariable Long gameId, @RequestBody List<UnitResultDTO> results) {
        gameService.saveResults(gameId, results);
    }

    @GetMapping("/scores")
    public Map<String, Integer> getTotalScores() {
        return gameService.calculateTotalScores();
    }
    @GetMapping("/scores/{date}")
    public Map<String, Integer> getScoresByDate(@PathVariable String date) {
        return gameService.calculateScoresByDate(LocalDate.parse(date));
    }
}