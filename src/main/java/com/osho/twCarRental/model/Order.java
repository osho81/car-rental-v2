package com.osho.twCarRental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "order" is reserved by sql
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "orderid")
    private Long id;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "firstrentalday")
    private LocalDate firstRentalDay;

    @Column(name = "lastrentalday")
    private LocalDate lastRentalDay;

    @Column(name = "customerid")
    private int customerId; // One customer per order

    // Creates new table for multiple mapping between cars and orders
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orderedcars",
            joinColumns = @JoinColumn(name = "carid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderid", referencedColumnName = "id"))
    private List<Car> orderedCars = new ArrayList<>();

    @Transient // To calculate price etc., without storing number of days
    private int numberOfDays;

    public Order() {
    }

    public Order(LocalDateTime orderDate, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId, List<Car> orderedCars) {
        this.orderDate = orderDate;
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.orderedCars = orderedCars;
    }
    public Order(LocalDateTime orderDate, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId) {
        this.orderDate = orderDate;
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.orderedCars = orderedCars;
    }

    public Order(Long id, LocalDateTime orderDate, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId, List<Car> orderedCars) {
        this.id = id;
        this.orderDate = orderDate;
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.orderedCars = orderedCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getFirstRentalDay() {
        return firstRentalDay;
    }

    public void setFirstRentalDay(LocalDate firstRentalDay) {
        this.firstRentalDay = firstRentalDay;
    }

    public LocalDate getLastRentalDay() {
        return lastRentalDay;
    }

    public void setLastRentalDay(LocalDate lastRentalDay) {
        this.lastRentalDay = lastRentalDay;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Solves the infinite recursion problem (uncomment if needed)
//    @JsonManagedReference
    public List<Car> getOrderedCars() {
        return orderedCars;
    }

    public void setOrderedCars(List<Car> orderedCars) {
        this.orderedCars = orderedCars;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
