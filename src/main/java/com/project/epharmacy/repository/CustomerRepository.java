package com.project.epharmacy.repository;

import com.project.epharmacy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByActive(Integer active);

    Customer findCustomerByIdAndActive(Long customerId, Integer active);

    Customer findByConfirmationTokenAndActive(String confirmationToken, Integer active);

    Customer findByEmailAndActive(String email, Integer active);

    List<Customer> findAllByConfirmationStatusAndActive(String confirmationStatus, Integer active);
}
