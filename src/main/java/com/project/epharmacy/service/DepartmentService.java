package com.project.epharmacy.service;

import com.project.epharmacy.dto.request.ReqDepartment;
import com.project.epharmacy.dto.response.RespDepartment;
import com.project.epharmacy.dto.response.RespMedication;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {

    Response<List<RespDepartment>> getDepartmentList();

    Response<RespDepartment> getDepartmentById(Long departmentId);

    Response addDepartment(ReqDepartment reqDepartment);

    Response updateDepartment(ReqDepartment reqDepartment);

}
