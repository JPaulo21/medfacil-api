package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.Alert;
import com.api.medfacil.domain.repositories.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public void save(Alert alert){
        alertRepository.save(alert);
    }

}
