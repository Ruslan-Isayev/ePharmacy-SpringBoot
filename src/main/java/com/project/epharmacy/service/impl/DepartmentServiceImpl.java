package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.request.ReqDepartment;
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

    @Override
    public Response<RespDepartment> getDepartmentById(ReqDepartment reqDepartment) {
        Response<RespDepartment> response = new Response<>();
        try {
            if (reqDepartment.getId() == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Department department = departmentRepository.getDepartmentByIdAndActive(reqDepartment.getId(), EnumAvavilableStatus.ACTIVE.value);
            RespDepartment respDepartment = mapping(department);
            response.setT(respDepartment);
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

    @Override
    public Response addDepartment(ReqDepartment reqDepartment) {
        Response response = new Response<>();
        try {
            String name = reqDepartment.getName();
            String location = reqDepartment.getLocation();
            if (name == null || location == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Department department = Department.builder()
                    .name(name)
                    .location(location)
                    .build();
            departmentRepository.save(department);
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

    @Override
    public Response updateDepartment(ReqDepartment reqDepartment) {
        Response response = new Response<>();
        try {
            Long id = reqDepartment.getId();
            String name = reqDepartment.getName();
            if (id == null || name == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Department department = new Department();
            department.setName(name);
            department.setLocation(reqDepartment.getLocation());
            departmentRepository.save(department);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (MyException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteDepartment(ReqDepartment reqDepartment) {
        Response response = new Response<>();
        try {
            Long departmentId = reqDepartment.getId();
            if (departmentId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Department department = departmentRepository.getDepartmentByIdAndActive(departmentId, EnumAvavilableStatus.ACTIVE.value);
            if (department == null) {
                throw new MyException(ExceptionConstants.DEPARTMENT_NOT_FOUND, "Department not found");
            }
            department.setActive(EnumAvavilableStatus.DEACTIVE.value);
            departmentRepository.save(department);
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

    private RespDepartment mapping(Department department) {
        RespDepartment respDepartment = RespDepartment.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .build();
        return respDepartment;
    }
}
