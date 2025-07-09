package com.andrew.campy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registrations")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String camperName;
    private String email;
    private String phoneNumber;
    private String age;
    private String comments;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "camp_id")
    private Camp camp;
}


