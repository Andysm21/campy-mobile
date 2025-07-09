package com.andrew.campy.service.impl;


import com.andrew.campy.dto.GameDTO;
import com.andrew.campy.dto.UnitResultDTO;
import com.andrew.campy.entity.Game;
import com.andrew.campy.entity.GameType;
import com.andrew.campy.entity.UnitResult;
import com.andrew.campy.repository.GameRepository;
import com.andrew.campy.repository.UnitResultRepository;
import com.andrew.campy.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final UnitResultRepository unitResultRepository;

    @Override
    public Long createGame(GameDTO dto) {
        Game game = new Game();
        game.setName(dto.name);
        game.setType(dto.type);
        game.setScheduledDate(dto.scheduledDate);
        game = gameRepository.save(game);
        return game.getId();
    }

    @Override
    public void saveResults(Long gameId, List<UnitResultDTO> results) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        for (UnitResultDTO dto : results) {
            UnitResult result = new UnitResult();
            result.setGame(game);
            result.setUnitName(dto.unitName);
            result.setPosition(dto.position);
            unitResultRepository.save(result);
        }
    }

    @Override
    public Map<String, Integer> calculateTotalScores() {
        List<UnitResult> allResults = unitResultRepository.findAll();
        Map<String, Integer> scoreMap = new HashMap<>();

        for (UnitResult result : allResults) {
            String unit = result.getUnitName();
            int score = calculatePoints(result);
            scoreMap.put(unit, scoreMap.getOrDefault(unit, 0) + score);
        }

        return scoreMap;
    }

    private int calculatePoints(UnitResult result) {
        GameType type = result.getGame().getType();
        int pos = result.getPosition();

        switch (type) {
            case MAJOR:
                return switch (pos) {
                    case 1 -> 4;
                    case 2 -> 3;
                    case 3 -> 2;
                    case 4 -> 1;
                    default -> 0;
                };
            case MINOR:
                return pos == 1 ? 2 : pos == 2 ? 1 : 0;
            case MAJINOR:
                return pos == 1 ? 4 : 0;
            default:
                return 0;
        }
    }
    @Override
    public Map<String, Integer> calculateScoresByDate(LocalDate date) {
        List<Game> gamesOnDate = gameRepository.findByScheduledDate(date);
        Map<String, Integer> scoreMap = new HashMap<>();

        for (Game game : gamesOnDate) {
            List<UnitResult> results = unitResultRepository.findByGameId(game.getId());
            for (UnitResult result : results) {
                String unit = result.getUnitName();
                int score = calculatePoints(result);
                scoreMap.put(unit, scoreMap.getOrDefault(unit, 0) + score);
            }
        }
        return scoreMap;
    }
}