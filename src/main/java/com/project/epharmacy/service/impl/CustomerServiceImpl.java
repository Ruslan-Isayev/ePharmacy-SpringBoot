package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();

        return response;
    }
}
