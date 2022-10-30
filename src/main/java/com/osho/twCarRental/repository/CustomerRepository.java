package com.osho.twCarRental.repository;

import com.osho.twCarRental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Customized method
    Optional<Customer> findByEmail(String email);
}

