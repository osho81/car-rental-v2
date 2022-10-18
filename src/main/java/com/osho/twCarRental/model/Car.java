package com.osho.twCarRental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "carid")
    private int id;

    @Column(name = "regnr", nullable = false)
    private String regNr; // BMW, Audi etc.

    @Column(name = "model")
    private String model; // BMW, Audi etc.

    @Column(name = "type")
    private String type; // SUV, van, sedan

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "daily_sek")
    private double dailySek; // SEK by default

    // Many cars can also be in many orders, and vice versa
    // (Refer to variable name orderedCars in Order class, not column name in this case)
    @OneToMany(mappedBy = "carId")
    private List<Order> carOrders = new ArrayList<>();

    public Car() {
    }

    public Car(String regNr, String model, String type, int modelYear, double dailySek, List<Order> carOrders) {
        this.regNr = regNr;
        this.model = model;
        this.type = type;
        this.modelYear = modelYear;
        this.dailySek = dailySek;
        this.carOrders = carOrders;
    }

    public Car(int id, String regNr, String model, String type, int modelYear, double dailySek, List<Order> carOrders) {
        this.id = id;
        this.regNr = regNr;
        this.model = model;
        this.type = type;
        this.modelYear = modelYear;
        this.dailySek = dailySek;
        this.carOrders = carOrders;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public List<Order> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(List<Order> carOrders) {
        this.carOrders = carOrders;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", regNr='" + regNr + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", modelYear=" + modelYear +
                ", dailySek=" + dailySek +
//                ", carOrders=" + displayCarOrders() +
                ", carOrders=" + Arrays.toString(carOrders.toArray()) +
                '}';
    }

//    public String displayCarOrders() {
//        return Arrays.toString(carOrders.toArray());
//    }
}
