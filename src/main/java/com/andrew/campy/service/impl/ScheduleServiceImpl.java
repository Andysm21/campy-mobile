package com.andrew.campy.service.impl;


import com.andrew.campy.dto.ScheduleDTO;
import com.andrew.campy.dto.ScheduleResponseDTO;
import com.andrew.campy.entity.Game;
import com.andrew.campy.entity.Schedule;
import com.andrew.campy.repository.GameRepository;
import com.andrew.campy.repository.ScheduleRepository;
import com.andrew.campy.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final GameRepository gameRepository;

    @Override
    public ScheduleResponseDTO createSchedule(ScheduleDTO dto) {
        Schedule schedule = mapToEntity(dto);
        schedule = scheduleRepository.save(schedule);
        return mapToResponse(schedule);
    }

    @Override
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleDTO dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        schedule.setDate(dto.date);
        schedule.setStartTime(dto.startTime);
        schedule.setEndTime(dto.endTime);
        schedule.setActivity(dto.activity);

        if (dto.gameId != null) {
            Game game = gameRepository.findById(dto.gameId)
                    .orElseThrow(() -> new IllegalArgumentException("Game not found"));
            schedule.setGame(game);
        } else {
            schedule.setGame(null);
        }

        schedule = scheduleRepository.save(schedule);
        return mapToResponse(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponseDTO> getSchedulesByDate(LocalDate date) {
        return scheduleRepository.findByDate(date).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Schedule mapToEntity(ScheduleDTO dto) {
        Schedule s = new Schedule();
        s.setDate(dto.date);
        s.setStartTime(dto.startTime);
        s.setEndTime(dto.endTime);
        s.setActivity(dto.activity);

        if (dto.gameId != null) {
            Game game = gameRepository.findById(dto.gameId)
                    .orElseThrow(() -> new IllegalArgumentException("Game not found"));
            s.setGame(game);
        }

        return s;
    }

    private ScheduleResponseDTO mapToResponse(Schedule s) {
        ScheduleResponseDTO res = new ScheduleResponseDTO();
        res.id = s.getId();
        res.date = s.getDate();
        res.startTime = s.getStartTime();
        res.endTime = s.getEndTime();
        res.activity = s.getActivity();
        res.gameId = s.getGame() != null ? s.getGame().getId() : null;
        return res;
    }
}