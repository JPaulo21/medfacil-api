package com.api.medfacil.web.controller;

import com.api.medfacil.domain.entities.Alert;
import com.api.medfacil.domain.services.AlertService;
import com.api.medfacil.web.dto.alert.AlertDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<AlertDTO>> getAlertsTodayByUser(@PathVariable("id") Integer id, @PageableDefault(size = 10)Pageable pageable){
        Page<AlertDTO> alertDTOPage = alertService.getAlertTodayByUser(id, pageable);
        return ResponseEntity.ok(alertDTOPage);
    }

}
