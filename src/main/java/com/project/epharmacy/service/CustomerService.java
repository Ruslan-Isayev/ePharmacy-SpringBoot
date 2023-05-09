package com.project.epharmacy.service;

import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList();
}
