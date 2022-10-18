package com.osho.twCarRental.controller;

import com.osho.twCarRental.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

//    @Autowired
//    private CustomerService customerService;


    ////---------- READ (GET) -------------////
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getPersonById(@PathVariable int id) {
//        return new ResponseEntity<Customer>(customerService.SOME-METHOD(id), HttpStatus.OK);
        return null; // Temporary until CustomerService is done
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllPersons() {
        // return ResponseEntity.ok().body(customerService.SOME-METHOD()); // Alternatively
//        return new ResponseEntity<List<Customer>>(customerService.SOME-METHOD(), HttpStatus.OK);
        return null; // Temporary until CustomerService is done
    }

}
