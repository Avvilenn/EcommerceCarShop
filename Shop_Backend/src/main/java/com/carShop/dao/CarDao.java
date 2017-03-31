package com.carShop.dao;




import com.carShop.entity.Car;
import com.carShop.exception.CarDaoException;
import java.util.List;

public interface CarDao {
    Long addCar(Car car)throws CarDaoException;
    void updateCar(Car car)throws CarDaoException;
    Car getCar(Long carId)throws CarDaoException;
    List<Car> findCars(List<Long> carId)throws CarDaoException;
    List<Car> findCars()throws CarDaoException;
    void deleteCar (Long carId) throws CarDaoException;
}
