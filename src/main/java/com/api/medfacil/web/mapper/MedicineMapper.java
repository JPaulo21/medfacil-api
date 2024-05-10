package com.api.medfacil.web.mapper;

import com.api.medfacil.domain.entities.Medicine;
import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.services.UserService;
import com.api.medfacil.web.dto.medicine.MedicineDTO;
import com.api.medfacil.web.dto.medicine.MedicineQueryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class})
public interface MedicineMapper {

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "dosageRegimen", source = "dosageRegimen")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "dosageRegimen.typeFrequency", source = "dosageRegimen.typeFrequency")
    Medicine toEntity(MedicineDTO medicineDTO);

    @Mapping(target = "userId", source = "user.id")
    MedicineDTO toDTO(Medicine medicine);

    MedicineQueryDTO toMedicineQueryDTO(Medicine medicine);

    List<MedicineDTO> toListMedicineDto(List<Medicine> medicines);

    default User mapUserIdtoUser(Integer userId, UserService userService){
        return userService.findById(userId);
    }

}
