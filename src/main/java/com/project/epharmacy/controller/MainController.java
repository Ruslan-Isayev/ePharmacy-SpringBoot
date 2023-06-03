package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.RespDepartment;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.CustomerService;
import com.project.epharmacy.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class MainController {

    private final CustomerService customerService;
    private final DepartmentService departmentService;

    @GetMapping("/getCustomerView/{reqToken}")
    public ModelAndView getCustomerView(@PathVariable("reqToken") ReqToken reqToken) {
        ModelAndView model = new ModelAndView("customer");
        Response<List<RespCustomer>> result = customerService.getCustomerList(reqToken);
        if (result.getStatus().getCode() == 1) {
            model.addObject("result", result.getT());
        } else {
            model.addObject("message", result.getStatus().getMessage());
        }
        return model;
    }

    @GetMapping("/getDepartmentView")
    public ModelAndView getDepartmentView() {
        ModelAndView model = new ModelAndView("department");
        Response<List<RespDepartment>> result = departmentService.getDepartmentList();
        if (result.getStatus().getCode() == 1) {
            model.addObject("result", result.getT());
        } else {
            model.addObject("message", result.getStatus().getMessage());
        }
        return model;
    }
}
