package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Customer;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.CustomerRepository;
import com.project.epharmacy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        try {
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvavilableStatus.ACTIVE.value);
            if (customerList.isEmpty()) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<RespCustomer> respCustomerList = customerList.stream().map(customer -> mapping(customer)).collect(Collectors.toList());
            response.setT(respCustomerList);
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

    private RespCustomer mapping(Customer customer) {
        RespCustomer respCustomer = RespCustomer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .cif(customer.getCif())
                .phone(customer.getPhone())
                .dob(customer.getDob())
                .build();
        return respCustomer;
    }
}
