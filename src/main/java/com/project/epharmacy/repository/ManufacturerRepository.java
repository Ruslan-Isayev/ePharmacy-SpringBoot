package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Manufacturer;
import com.project.epharmacy.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Manufacturer findManufacturerByIdAndActive(Long id, Integer active);

    List<Manufacturer> findAllByActive(Integer active);

    Manufacturer findManufacturerByMedicationAndActive(Medication medication, Integer active);
}
