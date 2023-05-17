package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Manufacturer findManufacturerByIdAndActive(Long id, Integer active);
}
