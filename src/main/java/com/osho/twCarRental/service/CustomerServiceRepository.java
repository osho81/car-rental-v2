package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Customer;

import java.util.List;

public interface CustomerServiceRepository {

    //-------- READ (GET) --------//
    Customer getCustomer(int id);
    List<Customer> getAllCustomers();


}
