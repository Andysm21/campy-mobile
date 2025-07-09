package com.andrew.campy.service;


import com.andrew.campy.dto.ScheduleDTO;
import com.andrew.campy.dto.ScheduleResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO createSchedule(ScheduleDTO dto);
    ScheduleResponseDTO updateSchedule(Long id, ScheduleDTO dto);
    void deleteSchedule(Long id);
    List<ScheduleResponseDTO> getAllSchedules();
    List<ScheduleResponseDTO> getSchedulesByDate(LocalDate date);
}