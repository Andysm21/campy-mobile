package com.andrew.campy.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit_results")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UnitResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unitName; // e.g., "Red", "Blue", etc.

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer position; // 1 for 1st, 2 for 2nd...
}
