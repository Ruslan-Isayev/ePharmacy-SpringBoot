package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Manufacturer;
import com.project.epharmacy.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByActive(Integer active);

    Medication findMedicationByIdAndActive(Long id, Integer active);

    List<Medication> findAllByManufacturerAndActive(Manufacturer manufacturer, Integer active);
}
