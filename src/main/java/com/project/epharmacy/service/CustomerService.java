package com.project.epharmacy.service;

import com.project.epharmacy.dto.request.ReqCustomer;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList(ReqToken reqToken);

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(ReqCustomer reqCustomer);

    Response confirmCustomer(String confirmationToken);

    void sendConfirmationEmail(String recipientEmail, String confirmationToken);
}
