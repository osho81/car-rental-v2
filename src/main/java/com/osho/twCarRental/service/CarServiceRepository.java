package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Car;
import java.util.List;

public interface CarServiceRepository {

    //-------- READ (GET) --------//
    Car getCarById(int id);
    List<Car> getAllCars();

    //------ CREATE (SAVE) ------//
    Car addCar(Car car);

}
