package com.api.medfacil.web.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserDTO (
        @CPF(message = "CPF inválido!")
        @Schema(example = "64112312067")
        String cpf,

        @NotBlank(message = "Nome não pode estar branco/nulo")
        @Pattern(regexp = "[a-zA-ZÀ-ÿ\\s]+$", message = "Somente letras são permitidos")
        @Schema(example = "João Paulo")
        String name,

        @Past
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(example = "09/03/1999")
        LocalDate birthDate,

        @Length(min = 1, max = 1)
        @Schema(type = "string", example = "M")
        String sex,

        @NotNull(message = "Objeto contact deve ser preenchido")
        ContactDTO contact

){
}
