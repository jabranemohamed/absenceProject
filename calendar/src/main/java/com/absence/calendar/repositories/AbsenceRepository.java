package com.absence.calendar.repositories;

import com.absence.calendar.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
}
