package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.repository.CustomerRepository;
import com.osho.twCarRental.service.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerService implements CustomerServiceRepository {

    @Autowired
    private CustomerRepository customerRepository;


    ////---------------- READ (GET) -----------------////

    @Override  // NOT IN PROJECT REQUIREMENT
    public Customer getCustomerById(int id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        } else {
            throw new RuntimeException("Person with id " + id + " not found.");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
