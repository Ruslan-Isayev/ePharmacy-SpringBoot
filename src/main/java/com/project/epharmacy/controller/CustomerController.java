package com.project.epharmacy.controller;

import com.project.epharmacy.dto.request.ReqCustomer;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/getCustomerList")
    public Response<List<RespCustomer>> getCustomerList(@RequestBody ReqToken reqToken) {
        return customerService.getCustomerList(reqToken);
    }

    @PostMapping("/getCustomerById")
    public Response<RespCustomer> getCustomerById(@RequestBody ReqCustomer reqCustomer) {
        return customerService.getCustomerById(reqCustomer);
    }

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/updateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/deleteCustomer")
    public Response deleteCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.deleteCustomer(reqCustomer);
    }

    @GetMapping("/confirmCustomer/{confirmationToken}")
    public Response confirmCustomer(@PathVariable String confirmationToken) {
        return customerService.confirmCustomer(confirmationToken);
    }
}