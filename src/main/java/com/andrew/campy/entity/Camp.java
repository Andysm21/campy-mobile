package com.andrew.campy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "camps")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String type;   // e.g. "Summer", "Winter"

    private BigDecimal price;


}
