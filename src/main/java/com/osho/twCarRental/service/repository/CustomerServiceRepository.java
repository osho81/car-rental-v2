package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Customer;

import java.util.List;

public interface CustomerServiceRepository {

    //---- PROJECT REQUIREMENTS ----//
    List<Customer> getAllCustomers();


    //-- NOT PROJECT REQUIREMENTS --//
    Customer addCustomer(Customer customer);
    void deleteCustomer(Customer customer);
}
