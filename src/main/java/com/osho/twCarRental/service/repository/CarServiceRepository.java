package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Car;
import java.util.List;

public interface CarServiceRepository {

    //---- PROJECT REQUIREMENTS ----//
    List<Car> getAllCars();
    Car addCar(Car car);
    Car updateCar(Car car);
    void deleteCar(Car car);


    //-- NOT PROJECT REQUIREMENTS --//
    Car getCarById(int id);
    Car updateCarById(int id, Car car);
    void deleteCarById(int id);

}
