package com.osho.twCarRental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "orders") // "order" is reserved by sql
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "orderid")
    private int id;

    @Column(name = "order_nr")
    private String orderNr;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "first_rental_day")
    private LocalDate firstRentalDay;

    @Column(name = "last_rental_day")
    private LocalDate lastRentalDay;

    @Column(name = "price")
    private double price;

    @Column(name = "customer_id")
    private int customerId; // One customer per order

//    // Don't need manyToMany between Cars and Orders
//    @ManyToMany(cascade = CascadeType.DETACH)
//    @JoinTable(
//            name = "ordered_cars",
//            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
//    private List<Car> orderedCars = new ArrayList<>();

    @Column(name = "car_id")
    private int carId;

    //    @Transient // To calculate price etc., without storing number of days
    @Column(name = "num_of_days")
    private int numberOfDays;

    public Order() {
    }

    public Order(String orderNr, LocalDateTime orderDate, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId, int carId) {
        this.orderNr = orderNr;
        this.orderDate = orderDate;
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.carId = carId;
//        this.orderedCars = orderedCars;
        setNumberOfDays(firstRentalDay, lastRentalDay);
    }

    public Order(int id, String orderNr, LocalDateTime orderDate, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId, int carId) {
        this.id = id;
        this.orderNr = orderNr;
        this.orderDate = orderDate;
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.carId = carId;
//        this.orderedCars = orderedCars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(String orderNr) {
        this.orderNr = orderNr;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double priceInclVat) {
        this.price = priceInclVat;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Solves the infinite recursion problem (uncomment if needed)
//    @JsonManagedReference
//    public List<Car> getOrderedCars() {
//        return orderedCars;
//    }
//
//    public void setOrderedCars(List<Car> orderedCars) {
//        this.orderedCars = orderedCars;
//    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(LocalDate firstRentalDay, LocalDate lastRentalDay) {
        // +1 to include first day; e.g. return same day would otherwise be 0 days
        this.numberOfDays = (int) DAYS.between(firstRentalDay, lastRentalDay) + 1;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNr='" + orderNr + '\'' +
                ", orderDate=" + orderDate +
                ", firstRentalDay=" + firstRentalDay +
                ", lastRentalDay=" + lastRentalDay +
                ", price=" + price +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
