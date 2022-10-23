package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Customer;

import java.util.List;

public interface CustomerServiceRepository {

    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();


}
