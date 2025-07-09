package com.andrew.campy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampResponseDTO {
    public Long id;
    public String name;
    public LocalDate startDate;
    public LocalDate endDate;
    public String type;
    public BigDecimal price;
}