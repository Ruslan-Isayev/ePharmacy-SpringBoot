package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByActive(Integer active);
}
