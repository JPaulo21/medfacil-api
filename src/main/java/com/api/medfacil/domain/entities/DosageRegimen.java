package com.api.medfacil.domain.entities;

import com.api.medfacil.domain.entities.enums.TypeFrequency;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class DosageRegimen {

    @Column(name = "routes_of_administration", nullable = false)
    private String routesOfAdministration;

    @Column(nullable = false)
    private Integer frequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeFrequency typeFrequency;

    @Column(name = "start_medication", nullable = false)
    private LocalDateTime startMedication;

    @Column(name = "end_medication", nullable = false)
    private LocalDateTime endMedication;

    @Column(nullable = false)
    private String observation;

}
