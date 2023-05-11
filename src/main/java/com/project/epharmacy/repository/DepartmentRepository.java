package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByActive(Integer active);

    Department getDepartmentByIdAndActive(Long departmentId, Integer active);
}
