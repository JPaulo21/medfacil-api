package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.Alert;
import com.api.medfacil.domain.entities.Medicine;
import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.entities.enums.TypeFrequency;
import com.api.medfacil.domain.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final AuthService authService;
    private final AlertService alertService;

    @Transactional
    public Medicine save(Medicine medicineData){

        Medicine medicine = medicineRepository.save(medicineData);

        if (medicine.getDosageRegimen().getTypeFrequency().equals(TypeFrequency.HOUR)){
            LocalDateTime hourMedication = medicine.getDosageRegimen().getStartMedication();
            while(hourMedication.isBefore(medicine.getDosageRegimen().getEndMedication())){
                hourMedication = hourMedication.plusHours(medicine.getDosageRegimen().getFrequency());
                LocalDate dateAlert = LocalDate.from(hourMedication);
                LocalTime hourAlert = LocalTime.from(hourMedication);

                Alert alert = Alert.builder()
                        .dateAlert(dateAlert)
                        .hourAlert(hourAlert)
                        .message(String.format("Eiii não esqueça de tomar %s, ", medicine.getName()))
                        .user(medicine.getUser())
                        .medicine(medicine)
                        .confirmed(Boolean.FALSE)
                        .build();
                alertService.save(alert);
            }
        }

        return medicine;
    }

    public Page<Medicine> getMedicinesByUser(Pageable page) {
        User user = authService.getUserAuthentication();
        Page<Medicine> medicinePage = medicineRepository.findByUserId(user.getId(), page);

        return medicinePage;
    }
}
