package com.api.medfacil.domain.repositories;

import com.api.medfacil.domain.entities.Caregiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarigiverRepository extends JpaRepository<Caregiver, Integer> {

    Page<Caregiver> findByUserId(Integer id, Pageable pageable);
}
