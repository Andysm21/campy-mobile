package com.andrew.campy.controller;


import com.andrew.campy.dto.ScheduleDTO;
import com.andrew.campy.dto.ScheduleResponseDTO;
import com.andrew.campy.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponseDTO create(@RequestBody ScheduleDTO dto) {
        return scheduleService.createSchedule(dto);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDTO update(@PathVariable Long id, @RequestBody ScheduleDTO dto) {
        return scheduleService.updateSchedule(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }

    @GetMapping
    public List<ScheduleResponseDTO> getAll() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{date}")
    public List<ScheduleResponseDTO> getByDate(@PathVariable String date) {
        return scheduleService.getSchedulesByDate(LocalDate.parse(date));
    }
}