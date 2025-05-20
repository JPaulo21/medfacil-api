package com.api.medfacil.web.dto.user;

public record UserQueryDTO(
        Integer id,
        String cpf,
        String name
) {
}
