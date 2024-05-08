package com.api.medfacil.domain.entities;

import com.api.medfacil.domain.entities.enums.TypeFrequency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class DosageRegimen {

    @Column(name = "routes of administration", nullable = false)
    private String routesOfAdministration;

    @Column(nullable = false)
    private Integer frequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeFrequency typeFrequency;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "medication_time", nullable = false)
    private Time duration;

    @Column(nullable = false)
    private String observation;

    @Column(nullable = false)
    private Boolean enabled;

}
