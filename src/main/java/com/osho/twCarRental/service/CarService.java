package com.osho.twCarRental.service;

import com.osho.twCarRental.model.Car;
import com.osho.twCarRental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceRepository {

    @Autowired
    private CarRepository carRepository;

    ////--------------- READ (GET) ----------------////

    @Override
    public Car getCarById(int id) {
        Optional<Car> foundCar = carRepository.findById(id);
        if (foundCar.isPresent()) {
            return foundCar.get();
        } else {
            throw new RuntimeException("Car with id " + id + " not found.");
        }
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    ////-------------- CREATE (SAVE) --------------////
    public Car addCar(Car car) {
        Optional<Car> foundByEmail = carRepository.findByRegNr(car.getRegNr());
        if (foundByEmail.isPresent()) { // Check if regNr is occupied
            throw new IllegalStateException(car.getRegNr() + " already in our fleet.");
        } else {
            System.out.println(car.getModel() + " with reg. nr " + car.getRegNr() + " is added.");
            carRepository.save(car);
        }
        return carRepository.save(car);
    }
}
