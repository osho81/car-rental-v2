package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Customer;

import java.util.List;

public interface CustomerServiceRepository {

    //-------- READ (GET) --------//
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();


}
