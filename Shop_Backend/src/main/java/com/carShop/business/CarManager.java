package com.carShop.business;


import com.carShop.dao.CarDao;
import com.carShop.dao.GlobalDaoFactory;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;
import com.carShop.exception.CarDaoException;

import java.util.List;

public class CarManager {
    private static CarManager instance = new CarManager();
    private CarDao dao = GlobalDaoFactory.getCarDao();


    private CarManager (){};

    public static CarManager getInstance (){
        return instance;
    }

    public void setDao(CarDao dao) {
        this.dao = dao;
    }



    public Long addCar(Car car) throws CarBusinessException {
        try {
            Long id = dao.addCar(car);
            return id;
        } catch (CarDaoException ex) {
            throw new CarBusinessException(ex.getMessage(), 12);
        } finally {
            System.out.println("Class CarManager, method addCar, new car added" + car);
        }
    }

    public void updateCar(Car car) throws CarBusinessException {
        try {
            dao.updateCar(car);
        } catch (CarDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 12);
        }
    }

    public void deleteCar(Long carId) throws CarBusinessException {
        try {
            dao.deleteCar(carId);
        } catch (CarDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 12);
        }
    }

    public Car getCar(Long carId) throws CarBusinessException {
        try {
            Car car = dao.getCar(carId);
            return car;
        } catch (CarDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 12);
        }


    }

    public List<Car> findCars(List<Long> carId) throws CarBusinessException {
        try {
            List<Car> searchResult = dao.findCars(carId);
            return searchResult;
        } catch (CarDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 12);
        }

    }
    public List<Car> findCars() throws CarBusinessException {
        try {
            List<Car> searchResult = dao.findCars();
            return searchResult;
        } catch (CarDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 12);
        }

    }

}
