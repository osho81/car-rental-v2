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
    private LocalDateTime orderOrUpdateTime;

    @Column(name = "first_rental_day")
    private LocalDate firstRentalDay;

    @Column(name = "last_rental_day")
    private LocalDate lastRentalDay;

    @Column(name = "customer_id")
    private int customerId; // One customer per order

    @Column(name = "car_id")
    private int carId;
    @Column(name = "price")
    private double price;

    @Column(name = "num_of_days")
    private int numberOfDays;


    public Order() {
    }

    public Order(String orderNr, LocalDateTime orderOrUpdateTime, LocalDate firstRentalDay, LocalDate lastRentalDay,
                 int customerId, int carId, double price, int numberOfDays) {
        this.orderNr = orderNr;
        this.orderOrUpdateTime = orderOrUpdateTime.withNano(0); // Remove nano seconds
        this.firstRentalDay = firstRentalDay;
        this.lastRentalDay = lastRentalDay;
        this.customerId = customerId;
        this.carId = carId;
        this.numberOfDays = numberOfDays;
        this.price = price;
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

    public LocalDateTime getOrderOrUpdateTime() {
        return orderOrUpdateTime;
    }

    public void setOrderOrUpdateTime(LocalDateTime orderOrUpdateTime) {
        this.orderOrUpdateTime = orderOrUpdateTime.withNano(0); // Remove nano seconds
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNr='" + orderNr + '\'' +
                ", orderDate=" + orderOrUpdateTime +
                ", firstRentalDay=" + firstRentalDay +
                ", lastRentalDay=" + lastRentalDay +
                ", price=" + price +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
