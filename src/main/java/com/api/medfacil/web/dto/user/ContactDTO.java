package com.api.medfacil.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDTO(

        @Email(message = "E-mail inválido!")
        @Schema(type = "string", example = "jp@email.com")
        String email,

        @NotBlank
        @Schema(type = "string", example = "+55")
        String ddi,

        @NotBlank
        @Schema(type = "string", example = "81")
        String ddd,

        @NotBlank
        @Schema(type = "string", example = "985640274")
        String phoneNumber
) {
}
