package com.api.medfacil.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ContactCaregiver")
@Table(name = "contact_caregiver")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactCaregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 4)
    private String ddi;

    @Column(length = 3)
    private String ddd;

    @Column(name = "phone_number", length = 9)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

}
