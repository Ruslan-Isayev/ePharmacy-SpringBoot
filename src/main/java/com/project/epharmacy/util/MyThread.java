package com.project.epharmacy.util;

import com.project.epharmacy.entity.Customer;
import com.project.epharmacy.enums.ConfirmationStatus;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.CustomerRepository;
import com.project.epharmacy.service.CustomerService;
import com.project.epharmacy.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class MyThread extends Thread {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    @Override
    public void run() {
        try {
            List<Customer> customerList = customerRepository.findAllByConfirmationStatusAndActive(ConfirmationStatus.PENDING.status, EnumAvavilableStatus.ACTIVE.value);

            if (customerList.isEmpty()) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }

            while (!customerList.isEmpty()) {
                for (Customer customer : customerList) {
                    customer.setConfirmationStatus(ConfirmationStatus.CONFIRMED.status);
                    customerService.sendConfirmationEmail(customer.getEmail(), customer.getConfirmationToken());
                }
                Thread.sleep(3000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
