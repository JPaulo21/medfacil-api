package com.api.medfacil.domain.repositories.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AlertProjection {
    public String getName();
    public String getDose();
    public LocalDate getDate_Alert();
    public LocalTime getHour_Alert();
    public Boolean getConfirmed();
}
