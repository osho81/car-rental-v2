package com.osho.twCarRental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    // Changed type from string to type 221224
    public enum Type {
        SEDAN, // 0; use index for postman, frontend, data.sql etc
        MINI, // 1
        SUV, // 2
        CAB, // 3
        SPORT, // 4
        BUS // 5
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "regnr", nullable = false)
    private String regNr; // BMW, Audi etc.

    @Column(name = "model")
    private String model; // BMW, Audi etc.

    @Column(name = "type")
    private Type type;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "daily_sek")
    private double dailySek; // SEK by default

    // A car can be in many orders, and vice versa
    // (Refers to variable name carId in Order class, not column name)
    @Column(name = "orders_of_car")
    @OneToMany(mappedBy = "carId", cascade = CascadeType.PERSIST)
    private List<Order> ordersOfCar = new ArrayList<>();

    // Empty constructor
    public Car() {
    }

    // Constructor without ID
    public Car(String regNr, String model, Type type, int modelYear, double dailySek, List<Order> ordersOfCar) {
        this.regNr = regNr;
        this.model = model;
        this.type = type;
        this.modelYear = modelYear;
        this.dailySek = dailySek;
        this.ordersOfCar = ordersOfCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int registrationNr) {
        this.modelYear = registrationNr;
    }

    public double getDailySek() {
        return dailySek;
    }

    public void setDailySek(double dailySek) {
        this.dailySek = dailySek;
    }

//    @JsonManagedReference // Solves the infinite recursion problem
    public List<Order> getOrdersOfCar() {
        return ordersOfCar;
    }

    public void setOrdersOfCar(List<Order> ordersOfCar) {
        this.ordersOfCar = ordersOfCar;
    }
}
