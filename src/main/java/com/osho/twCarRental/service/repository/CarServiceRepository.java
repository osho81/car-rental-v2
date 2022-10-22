package com.osho.twCarRental.service.repository;

import com.osho.twCarRental.model.Car;
import java.util.List;

public interface CarServiceRepository {

    //------- READ (GET) -------//
    Car getCarById(int id);
    List<Car> getAllCars();

    //------ CREATE (SAVE) ------//
    Car addCar(Car car);

    //--------- UPDATE ----------//
    Car updateCar(Car car);
    Car updateCarById(int id, Car car);

    //--------- DELETE ----------//

    void deleteCar(Car car);

    void deleteCarById(int id);

}
