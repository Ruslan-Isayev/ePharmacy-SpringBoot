package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqDepartment;
import com.project.epharmacy.dto.response.RespDepartment;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getDepartmentById/{departmentId}")
    public Response<RespDepartment> getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/addDepartment")
    public Response addDepartment(@RequestBody ReqDepartment reqDepartment) {
        return departmentService.addDepartment(reqDepartment);
    }

    @PutMapping("/updateDepartment")
    public Response updateDepartment(@RequestBody ReqDepartment reqDepartment) {
        return departmentService.updateDepartment(reqDepartment);
    }
}
