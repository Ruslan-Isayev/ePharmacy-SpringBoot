package com.project.epharmacy.service.impl;

import com.project.epharmacy.dto.request.ReqCustomer;
import com.project.epharmacy.dto.request.ReqToken;
import com.project.epharmacy.dto.response.RespCustomer;
import com.project.epharmacy.dto.response.RespStatus;
import com.project.epharmacy.dto.response.Response;
import com.project.epharmacy.entity.Customer;
import com.project.epharmacy.enums.ConfirmationStatus;
import com.project.epharmacy.enums.EnumAvavilableStatus;
import com.project.epharmacy.exception.ExceptionConstants;
import com.project.epharmacy.exception.MyException;
import com.project.epharmacy.repository.CustomerRepository;
import com.project.epharmacy.service.CustomerService;
import com.project.epharmacy.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    private final Utility utility;

    private final JavaMailSender mailSender;

    @Override
    public Response<List<RespCustomer>> getCustomerList(ReqToken reqToken) {
        Response<List<RespCustomer>> response = new Response<>();
        try {
            utility.checkToken(reqToken);
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

    @Override
    public Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer) {
        Response response = new Response<>();
        try {
            utility.checkToken(reqCustomer.getReqToken());
            Long customerId = reqCustomer.getId();
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvavilableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            RespCustomer respCustomer = mapping(customer);
            response.setT(respCustomer);
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

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            utility.checkToken(reqCustomer.getReqToken());
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            if (reqCustomer.getEmail() == null) {
                throw new IllegalArgumentException("Recipient email address is null");
            }
            Customer customer = Customer.builder()
                    .name(name)
                    .surname(surname)
                    .email(reqCustomer.getEmail())
                    .phone(reqCustomer.getPhone())
                    .dob(reqCustomer.getDob())
                    .cif(reqCustomer.getCif())
                    .confirmationToken(UUID.randomUUID().toString())
                    .build();
            customerRepository.save(customer);
            sendConfirmationEmail(customer.getEmail(), customer.getConfirmationToken());
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

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response<>();
        try {
            utility.checkToken(reqCustomer.getReqToken());
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            Long id = reqCustomer.getId();
            if (name == null || surname == null || id == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(reqCustomer.getDob());
            customer.setCif(reqCustomer.getCif());
            customer.setPhone(reqCustomer.getPhone());
            customerRepository.save(customer);
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

    @Override
    public Response deleteCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            utility.checkToken(reqCustomer.getReqToken());
            Long customerId = reqCustomer.getId();
            if (customerId == null) {
                throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvavilableStatus.ACTIVE.value);
            if (customer == null) {
                throw new MyException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            customer.setActive(EnumAvavilableStatus.DEACTIVE.value);
            customerRepository.save(customer);
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

    @Override
    public Response confirmCustomer(String confirmationToken) {
        Response response = new Response();
        try {
            Customer customer = customerRepository.findByConfirmationToken(confirmationToken);
            if (customer == null) {
                throw new MyException(ExceptionConstants.INVALID_CONFIRMATION_TOKEN, "Invalid confirmation token");
            }
            customer.setConfirmationStatus(ConfirmationStatus.CONFIRMED.status);
            customerRepository.save(customer);
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
                .email(customer.getEmail())
                .cif(customer.getCif())
                .phone(customer.getPhone())
                .dob(customer.getDob())
                .confirmationToken(customer.getConfirmationToken())
                .build();
        return respCustomer;
    }

    private void sendConfirmationEmail(String recipientEmail, String confirmationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Email Confirmation");
        message.setText("Please confirm your email address by clicking the link below:\n\n"
                + "http://localhost:8091/customer/confirmCustomer/" + confirmationToken);
        mailSender.send(message);
    }
}
