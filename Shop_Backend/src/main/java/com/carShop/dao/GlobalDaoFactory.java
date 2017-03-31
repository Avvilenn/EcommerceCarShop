package com.carShop.dao;;

import com.carShop.dao.CarDao;
import com.carShop.dao.CarOrderDao;
import com.carShop.dao.CarOrderItemDao;
import com.carShop.dao.PersonDao;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class GlobalDaoFactory {
    private static CarDao carDao;
    private static CarOrderDao carOrderDao;
    private static PersonDao personDao;
    private static CarOrderItemDao orderItemDao;


    public synchronized static CarDao getCarDao() {
        if (carDao == null) {
            try {
                createAllDao();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return carDao;
    }


    public synchronized static CarOrderDao getCarOrderDao() {
        if (carOrderDao == null) {
            try {
                createAllDao();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return carOrderDao;
    }

    public synchronized static PersonDao getPersonDao() {
        if (personDao == null) {
            try {
                createAllDao();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return personDao;
    }

    public synchronized static CarOrderItemDao getOrderItemDao() {
        if (orderItemDao == null) {
            try {
                createAllDao();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return orderItemDao;
    }

    public synchronized static void createAllDao() throws Exception {
        PropertyResourceBundle p = (PropertyResourceBundle) ResourceBundle.getBundle("DaoProperties");

        String carDaoStr = p.getString("CarDao");
        carDao = (CarDao) Class.forName(carDaoStr).newInstance();

        String personDaoStr = p.getString("PersonDao");
        personDao = (PersonDao) Class.forName(personDaoStr).newInstance();

        String carOrderDaoStr = p.getString("CarOrderDao");
        carOrderDao = (CarOrderDao) Class.forName(carOrderDaoStr).newInstance();

        String orderItemDaoStr = p.getString("CarOrderItemDao");
        orderItemDao = (CarOrderItemDao) Class.forName(orderItemDaoStr).newInstance();
    }

}
