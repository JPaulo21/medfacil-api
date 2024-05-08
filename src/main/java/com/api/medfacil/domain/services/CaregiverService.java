package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.repositories.CarigiverRepository;
import com.api.medfacil.domain.entities.Caregiver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CaregiverService {

    private final CarigiverRepository carigiverRepository;
    private final AuthService authService;

    public Caregiver save(Caregiver caregiver) {

        return carigiverRepository.save(caregiver);
    }

    public Page<Caregiver> getCaregiverByUser(Pageable pageable) {
        User user = authService.getUserAuthentication();
        Page<Caregiver> caregiverPage = carigiverRepository.findByUserId(user.getId(), pageable);
        return caregiverPage;
    }

    @Transactional(readOnly = true)
    public Caregiver getCaregiverId(Integer id)  {
        return carigiverRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Caregiver id:  not found")
        );
    }
}
