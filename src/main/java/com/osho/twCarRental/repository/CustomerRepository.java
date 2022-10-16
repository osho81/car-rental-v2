package com.osho.twCarRental.repository;

import com.osho.twCarRental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {



}
