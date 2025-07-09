package com.andrew.campy.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleResponseDTO {
    public Long id;
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
    public String activity;
    public Long gameId;
}