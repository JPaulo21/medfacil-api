package com.api.medfacil.domain.repositories;

import com.api.medfacil.domain.entities.Alert;
import com.api.medfacil.domain.repositories.projections.AlertProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    @Query(value = """
                    SELECT 
                        m.name, 
                        m.dose, 
                        a.date_alert,
                        a.hour_alert, 
                        a.confirmed, 
                    FROM medicines m 
                    INNER JOIN alerts a ON m.id = a.medicine_id 
                    WHERE a.user_id = :id 
                    AND a.date_alert = current_date """,
            nativeQuery = true)
    Page<AlertProjection> findAlertsTodayByUser(Integer id, Pageable pageable);
}