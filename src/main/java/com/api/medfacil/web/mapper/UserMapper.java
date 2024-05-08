package com.api.medfacil.web.mapper;

import com.api.medfacil.domain.entities.User;
import com.api.medfacil.web.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "enable", constant = "true")
    @Mapping(target = "contact", source = "contact") // Composição de objetos devem ser mapeados
    User toUser(UserDTO userDTO);
}
