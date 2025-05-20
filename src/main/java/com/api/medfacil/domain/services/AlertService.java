package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.Alert;
import com.api.medfacil.domain.repositories.AlertRepository;
import com.api.medfacil.web.dto.alert.AlertDTO;
import com.api.medfacil.domain.repositories.projections.AlertProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public void save(Alert alert){
        alertRepository.save(alert);
    }

    public Page<AlertDTO> getAlertTodayByUser(Integer id, Pageable pageable) {
        Page<AlertProjection> alertProjection = alertRepository.findAlertsTodayByUser(id, pageable);

        List<AlertDTO> alertsDTO = alertProjection
                .getContent()
                .stream()
                .map(a -> new AlertDTO(a.getName(), a.getDose(), a.getDate_Alert(), a.getHour_Alert(), a.getConfirmed()))
                .toList();

        Page<AlertDTO> alertDTOPage = new PageImpl<>(alertsDTO, alertProjection.getPageable(), alertProjection.getTotalElements());
        return alertDTOPage;
    }
}
