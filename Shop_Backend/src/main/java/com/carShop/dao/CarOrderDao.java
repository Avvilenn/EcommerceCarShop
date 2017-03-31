package com.carShop.dao;


import com.carShop.entity.CarOrder;
import com.carShop.exception.CarOrderDaoException;
import java.util.List;

public interface CarOrderDao {

    Long addCarOrder(CarOrder order)throws CarOrderDaoException;
    void updateCarOrder(CarOrder order)throws CarOrderDaoException;
    CarOrder getCarOrder(long orderId)throws CarOrderDaoException;
    List<CarOrder> findOrders(Long orderId)throws CarOrderDaoException;
}
