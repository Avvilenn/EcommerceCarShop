package com.carShop.dao;


import com.carShop.entity.CarOrderItem;
import com.carShop.exception.CarOrderItemDaoException;

import java.util.List;

public interface CarOrderItemDao {

    Long addCarOrderItem(CarOrderItem orderItem)throws CarOrderItemDaoException;
    void updateCarOrderItem(CarOrderItem orderItem)throws CarOrderItemDaoException;
    CarOrderItem getCarOrderItem(Long OrderItemId)throws CarOrderItemDaoException;
    List<CarOrderItem> findCarOrderItems(Long carOrder)throws CarOrderItemDaoException;
}
