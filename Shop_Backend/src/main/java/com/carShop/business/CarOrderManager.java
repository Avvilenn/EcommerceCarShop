package com.carShop.business;



import com.carShop.dao.CarOrderDao;
import com.carShop.dao.GlobalDaoFactory;
import com.carShop.entity.CarOrder;
import com.carShop.exception.CarBusinessException;
import com.carShop.exception.CarOrderDaoException;
import java.util.List;

public class CarOrderManager
{
    private static CarOrderManager instance = new CarOrderManager();
    private CarOrderDao dao = GlobalDaoFactory.getCarOrderDao();


    private CarOrderManager() {
    }

    public static CarOrderManager getInstance() {
        return instance;
    }

    public void setDao(CarOrderDao dao) {
        this.dao = dao;
    }


    public Long addCarOrder(CarOrder carOrder) throws CarBusinessException {
        try {
            Long id = dao.addCarOrder(carOrder);
            System.out.println("Class CarOrderManager, new order added to database");
            return id;
        } catch (CarOrderDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 11);
        }

    }

    public void updateCarOrder (CarOrder order)throws CarBusinessException{
        try {
            dao.updateCarOrder(order);
        } catch (CarOrderDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 11);
        }

    }

    public CarOrder getCarOrder (Long id)throws CarBusinessException{
        try {
            CarOrder order = dao.getCarOrder(id);
            System.out.println("class CarOrderManager, method getCarOrder");
            return order;
        } catch (CarOrderDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 11);
        }

    }

    public List<CarOrder> findOrderItems(Long orderId) throws CarBusinessException {
        try {
            List<CarOrder> orders =  dao.findOrders(orderId);
            return  orders;
        } catch (CarOrderDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 11);
        }

    }



}
