package com.api.medfacil.domain.services;

import com.api.medfacil.domain.repositories.ContactCaregiverRepository;
import com.api.medfacil.domain.entities.ContactCaregiver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactCaregiverService {

    private final ContactCaregiverRepository contactCaregiverRepository;

    public ContactCaregiver save(ContactCaregiver contactCaregiver) {
        return contactCaregiverRepository.save(contactCaregiver);
    }
}
