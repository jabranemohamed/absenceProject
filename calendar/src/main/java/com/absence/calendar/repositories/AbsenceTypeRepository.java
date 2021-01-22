package com.absence.calendar.repositories;

import com.absence.calendar.entities.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceTypeRepository extends JpaRepository<AbsenceType,Long> {
}
