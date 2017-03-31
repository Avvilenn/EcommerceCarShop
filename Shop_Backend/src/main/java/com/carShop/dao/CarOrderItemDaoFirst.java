package com.carShop.dao;

import com.carShop.entity.Car;
import com.carShop.entity.CarOrderItem;
import com.carShop.entity.Person;
import com.carShop.exception.CarOrderItemDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.carShop.dao.GlobalDaoFactory.*;


public class CarOrderItemDaoFirst implements CarOrderItemDao {

    private Connection getConnection() throws Exception {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long addCarOrderItem(CarOrderItem orderItem) throws CarOrderItemDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "INSERT INTO car_order_items (car_id, car_order_id, person_id, count, price ) VALUES (?, ?, ?,?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, new String[]{
                        "person_id"
                });
                stmt.setLong(1, orderItem.getCar().getCarId());
                stmt.setLong(2, orderItem.getCarOrder().getCarOrderId());
                stmt.setLong(3, orderItem.getPerson().getPersonId());
                stmt.setInt(4, orderItem.getCount());
                stmt.setDouble(5, orderItem.getPrice());
                stmt.executeUpdate();
                ResultSet gk = stmt.getGeneratedKeys();
                Long savedId = 0L;
                if (gk.next()) {
                    savedId = gk.getLong(1);
                }
                gk.close();
                stmt.close();
                return savedId;
            } catch (SQLException e) {
                e.printStackTrace();
                CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
            throw s;
        }
    }

    @Override
    public void updateCarOrderItem(CarOrderItem orderItem) throws CarOrderItemDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "UPDATE car_order_items SET car_id=?, car_order_id=?, person_id=?, count=?, price=? WHERE car_order_item_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, orderItem.getCar().getCarId());
                stmt.setLong(2, orderItem.getCarOrder().getCarOrderId());
                stmt.setLong(3, orderItem.getPerson().getPersonId());
                stmt.setInt(4, orderItem.getCount());
                stmt.setDouble(5, orderItem.getPrice());
                stmt.setLong(6, orderItem.getCarOrderItemId());
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
            throw s;
        }
    }

    @Override
    public CarOrderItem getCarOrderItem(Long orderItemId) throws CarOrderItemDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "SELECT * FROM car_order_items WHERE car_order_item_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, orderItemId);
                ResultSet rs = stmt.executeQuery();
                CarOrderItem carOrderItem = null;
                while (rs.next()) {
                    carOrderItem = new CarOrderItem();
                    CarDao carDao = getCarDao();
                    Car car = carDao.getCar(rs.getLong("car_id"));
                    carOrderItem.setCar(car);
                    PersonDao personDao = getPersonDao();
                    Person person = personDao.getPerson(rs.getLong("person_id"));
                    carOrderItem.setPerson(person);
                    carOrderItem.setPrice(Double.parseDouble(String.valueOf(rs.getInt("price"))));
                    carOrderItem.setCount(rs.getInt("count"));
                }
                rs.close();
                stmt.close();
                return carOrderItem;
            } catch (Exception e) {
                e.printStackTrace();
                CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
                throw s;
            }  finally {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
            throw s;
        }
    }

    @Override
    public List<CarOrderItem> findCarOrderItems(Long carOrder) throws CarOrderItemDaoException {
        List<CarOrderItem> order = new ArrayList<>();
        try {
            Connection con = getConnection();
            try {
                String sql = "SELECT * FROM car_order_items WHERE car_order_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, carOrder);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    CarOrderItem carOrderItem = new CarOrderItem();
                    CarDao carDao = getCarDao();
                    Car car = carDao.getCar(rs.getLong("car_id"));
                    carOrderItem.setCar(car);
                    PersonDao personDao =  getPersonDao();
                    Person person = personDao.getPerson(rs.getLong("person_id"));
                    carOrderItem.setPerson(person);
                    carOrderItem.setPrice(Double.parseDouble(String.valueOf(rs.getInt("price"))));
                    carOrderItem.setCount(rs.getInt("count"));
                    order.add(carOrderItem);
                }
                rs.close();
                stmt.close();
                return order;
            } catch (Exception e) {
                e.printStackTrace();
                CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
                throw s;
            }  finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderItemDaoException s = new CarOrderItemDaoException(10L, e.getMessage());
            throw s;
        }
    }


}
