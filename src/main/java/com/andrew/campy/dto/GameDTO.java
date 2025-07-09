package com.andrew.campy.dto;

import com.andrew.campy.entity.GameType;
import java.time.LocalDate;

public class GameDTO {
    public String name;
    public GameType type;
    public LocalDate scheduledDate;
}