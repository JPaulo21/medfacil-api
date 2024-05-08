package com.api.medfacil.domain.services;

import com.api.medfacil.entities.Medicine;
import com.api.medfacil.entities.User;
import com.api.medfacil.repositories.MedicineRepository;
import com.api.medfacil.repositories.UserRepository;
import com.api.medfacil.domain.entities.Medicine;
import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.entities.enums.TypeFrequency;
import com.api.medfacil.domain.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final AuthService authService;

    @Transactional
    public Medicine save(Medicine medicine){
        return medicineRepository.save(medicine);
    }

    public Page<Medicine> getMedicinesByUser(Pageable page) {
        User user = authService.getUserAuthentication();
        Page<Medicine> medicinePage = medicineRepository.findByUserId(user.getId(), page);

        return medicinePage;
    }
}
