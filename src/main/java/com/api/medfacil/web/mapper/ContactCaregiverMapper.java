package com.api.medfacil.web.mapper;

import com.api.medfacil.entities.ContactCaregiver;
import com.api.medfacil.web.dto.caregiver.ContactCaregiverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactCaregiverMapper {

    @Mapping(target = "caregiver",source = "caregiverId")
    ContactCaregiver toEntity(ContactCaregiverDTO contactCaregiverDTO);
}
