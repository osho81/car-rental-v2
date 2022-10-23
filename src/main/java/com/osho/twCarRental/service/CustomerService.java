package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Customer;
import com.osho.twCarRental.model.Order;
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

    //-----------------------------------------------------------------------//
    //------------------------- PROJECT REQUIREMENTS ------------------------//
    //-----------------------------------------------------------------------//

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    //-----------------------------------------------------------------------//
    //---------------------- NOT PROJECT REQUIREMENTS -----------------------//
    //-----------------------------------------------------------------------//

    @Override
    public Customer getCustomerById(int id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        } else {
            throw new RuntimeException("Person with id " + id + " not found.");
        }
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Optional<Customer> foundByEmail = customerRepository.findByEmail(customer.getEmail());
        if (foundByEmail.isPresent()) { // Check if regNr is occupied
            throw new RuntimeException(customer.getEmail() + " already in our register.");
        } else {
            System.out.println("Customer with email " + customer.getEmail() + " has been registered.");
            return customerRepository.save(customer);
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {
        // Get customer to delete with id if exists, else get with email if that exists
        Optional<Customer> foundById = customerRepository.findById(customer.getId());
        Optional<Customer> foundByEmail = customerRepository.findByEmail(customer.getEmail());
        if (foundById.isEmpty() && foundByEmail.isEmpty()) {
            throw new RuntimeException("Order with id " + customer.getId()
                    + " or order nr " + customer.getEmail() + " not found");
        }

        // Then, if either id or email exists, get customer by one of them, and delete
        Customer customerToUpdate = foundById.isPresent() ? customerRepository.findById(customer.getId()).get() :
                customerRepository.findByEmail(customer.getEmail()).get();

        customerRepository.delete(customerToUpdate);
    }
}
