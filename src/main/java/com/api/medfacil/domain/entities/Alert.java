package com.api.medfacil.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Alert")
@Table(name = "alerts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Alert {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @Column(name = "date_alert", nullable = false)
    private LocalDate dateAlert;

    @Column(name = "hour_alert", nullable = false)
    private LocalTime hourAlert;

    @Column(name = "confirmed",nullable = false)
    private Boolean confirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

}
