package com.api.medfacil.web.dto.medicine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MedicineDTO(

        @NotBlank
        @Schema(example = "Dramim")
        String name,

        @NotBlank
        @Schema(example = "1 comp")
        String dose,

        @NotNull
        DosageRegimenDTO dosageRegimen,

        List<MedicationTimeDTO> medicationTimes,

        @NotNull
        @Schema(example = "1")
        Integer userId
) {
}
