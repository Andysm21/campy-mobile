package com.andrew.campy.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;  // The day of the event within the camp

    private LocalTime startTime;  // Start time of the activity (e.g., 14:00)

    private LocalTime endTime;    // End time of the activity (e.g., 16:30)

    private String activity;  // e.g., "Opening Ceremony", "Campfire", etc.

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;  // Optional: links to a game if the scheduled event is a competition
}
