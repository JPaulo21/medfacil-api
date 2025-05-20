package com.api.medfacil.web.dto.alert;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AlertDTO(

        String name,
        String dose,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateAlert,
        @JsonFormat(pattern = "HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
        LocalTime hourAlert,
        Boolean confirmed

) {
}
