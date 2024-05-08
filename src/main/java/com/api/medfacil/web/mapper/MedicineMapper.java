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
    Medicine toMedicine(MedicineDTO medicineDTO);

    @Mapping(target = "userId", source = "user.id")
    MedicineDTO toMedicineDTO(Medicine medicine);

    MedicineQueryDTO toMedicineQueryDTO(Medicine medicine);

    List<MedicineDTO> toListMedicineDto(List<Medicine> medicines);

    default User mapUserIdtoUser(Integer userId, UserService userService){
        return userService.findById(userId);
    }

}
