package com.carShop.business;


import com.carShop.dao.CarOrderItemDao;
import com.carShop.dao.GlobalDaoFactory;
import com.carShop.entity.CarOrderItem;
import com.carShop.exception.CarBusinessException;
import com.carShop.exception.CarOrderItemDaoException;

import java.util.List;

public class CarOrderItemManager {
    private static CarOrderItemManager instance = new CarOrderItemManager();
    private CarOrderItemDao orderItemDao = GlobalDaoFactory.getOrderItemDao();

    public static CarOrderItemManager getInstance() {
        return instance;
    }

    private CarOrderItemManager() {
    }

    public void setOrderItemDao(CarOrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public Long addOrderItem(CarOrderItem item) throws CarBusinessException {

        try {
            Long id = orderItemDao.addCarOrderItem(item);
            return id;
        } catch (CarOrderItemDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 14);
        }

    }

    public Long updateOrderItem (CarOrderItem item)throws CarBusinessException{
        try {
            orderItemDao.updateCarOrderItem(item);
            return null;
        } catch (CarOrderItemDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 14);
        }

    }

    public CarOrderItem getCarOrderItem (Long id)throws CarBusinessException{
        try {
            orderItemDao.getCarOrderItem(id);
            return null;
        } catch (CarOrderItemDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 14);
        }

    }

    public List<CarOrderItem> findOrderItems(Long carOrder) throws CarBusinessException {
        try {
            return orderItemDao.findCarOrderItems(carOrder);
        } catch (CarOrderItemDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 14);
        }

    }


}
