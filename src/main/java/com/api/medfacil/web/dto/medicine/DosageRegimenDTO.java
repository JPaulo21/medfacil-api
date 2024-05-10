package com.api.medfacil.web.dto.medicine;

import com.api.medfacil.domain.entities.enums.TypeFrequency;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DosageRegimenDTO(

      @NotBlank
      String routesOfAdministration,

      @NotNull
      Integer frequency,

      @NotBlank
      TypeFrequency typeFrequency,

      @NotNull
      @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
      LocalDateTime startMedication,

      @NotNull
      @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
      LocalDateTime endMedication,

      String observation

) {
}
