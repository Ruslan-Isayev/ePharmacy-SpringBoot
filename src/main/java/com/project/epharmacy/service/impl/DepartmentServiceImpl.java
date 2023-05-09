package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.response.RespDepartment;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Department;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.DepartmentRepository;
import com.project.epharmacy.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Response<List<RespDepartment>> getDepartmentList() {
        Response<List<RespDepartment>> response = new Response<>();
        try {
            List<Department> departmentList = departmentRepository.findAllByActive(EnumAvavilableStatus.ACTIVE.value);
            if (departmentList.isEmpty()) {
                throw new MyException(ExceptionConstants.DEPARTMENT_NOT_FOUND, "Department not found");
            }
            List<RespDepartment> respDepartmentList = departmentList.stream().map(department -> mapping(department)).collect(Collectors.toList());
            response.setT(respDepartmentList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespDepartment mapping(Department department){
        RespDepartment respDepartment = RespDepartment.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .build();
        return respDepartment;
    }
}
