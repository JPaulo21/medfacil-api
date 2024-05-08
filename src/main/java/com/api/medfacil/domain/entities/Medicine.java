package com.api.medfacil.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Medicine")
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medicine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String dose;

    @Embedded
    private DosageRegimen dosageRegimen;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicationTime> medicationTimes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
