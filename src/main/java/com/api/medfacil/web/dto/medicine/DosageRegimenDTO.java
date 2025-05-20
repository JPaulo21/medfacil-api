package com.api.medfacil.web.dto.medicine;

import com.api.medfacil.domain.entities.enums.TypeFrequency;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DosageRegimenDTO(

      @NotBlank
      @Schema(example = "Oral")
      String routesOfAdministration,

      @NotNull
      @Schema(example = "6")
      Integer frequency,

      @NotBlank
      @Schema(example = "HOUR", allowableValues = {"HOUR", "DAY"})
      TypeFrequency typeFrequency,

      @NotNull
      @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
      @Schema(example = "07/05/2024 10:00", type = "string")
      LocalDateTime startMedication,

      @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
      @Schema(example = "17/05/2024 10:00", type = "string")
      LocalDateTime endMedication,

      @Schema(example = "Diluir em água")
      String observation

) {
}
