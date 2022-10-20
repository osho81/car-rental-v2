package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    ////---------- READ (GET) -------------////

    @GetMapping("/customers/{id}") // NOT A PROJECT REQUIREMENT
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    // "Lista kunder GET /api/v1/customers"
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        // return ResponseEntity.ok().body(customerService.getAllCustomers()); // Alternatively
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
    }


    ////---------- CREATE (SAVE) -------------////



}
