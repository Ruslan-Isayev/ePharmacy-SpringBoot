package com.project.epharmacy;

import com.project.epharmacy.repository.CustomerRepository;
import com.project.epharmacy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EPharmacyApplication {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    public static void main(String[] args) {
        SpringApplication.run(EPharmacyApplication.class, args);

//        MyThread myThread = new MyThread(customerService, customerRepository);
//        myThread.start();
    }
}
