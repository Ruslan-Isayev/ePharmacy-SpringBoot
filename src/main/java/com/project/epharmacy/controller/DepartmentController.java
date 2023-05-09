package com.project.epharmacy.controller;

import com.project.epharmacy.dto.response.RespDepartment;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/getDepartmentList")
    public Response<List<RespDepartment>> getDepartmentList() {
        return departmentService.getDepartmentList();
    }
}
