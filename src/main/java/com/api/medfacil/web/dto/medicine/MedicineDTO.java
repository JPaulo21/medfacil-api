package com.api.medfacil.web.dto.medicine;

import com.api.medfacil.domain.entities.DosageRegimen;
import com.api.medfacil.domain.entities.MedicationTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicineDTO(

        @NotBlank
        String name,

        @NotBlank
        String dose,

        @NotNull
        DosageRegimenDTO dosageRegimen,

        List<MedicationTime> medicationTimes,

        @NotNull
        Integer userId
) {
}
