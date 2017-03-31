package com.carShop.dao;



import com.carShop.business.CarOrderItemManager;
import com.carShop.entity.CarOrder;
import com.carShop.entity.CarOrderItem;
import com.carShop.exception.CarOrderDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CarOrderDaoFirst implements CarOrderDao
{

    private Connection getConnection() throws Exception {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long addCarOrder(CarOrder order) throws CarOrderDaoException {
        try {
            Connection con = getConnection();
            con.setAutoCommit(false);
            try {
                String sql = "INSERT INTO car_order (person_id, date_of_order ) VALUES (?, ?)";
                PreparedStatement stmt1 = con.prepareStatement(sql, new String[]{
                        "car_order_id"
                });
                stmt1.setLong(1, order.getPerson().getPersonId());
                stmt1.setDate(2, new java.sql.Date(order.getDateOfOrder().getTime()));
                stmt1.executeUpdate();
                ResultSet gk = stmt1.getGeneratedKeys();
                Long savedId = 0L;
                if (gk.next()) {
                    savedId = gk.getLong(1);
                }
                order.setCarOrderId(savedId);
                System.out.println("New Order ID:" + savedId);
                gk.close();
                stmt1.close();

                String sqlItem = "INSERT INTO car_order_items (car_id, car_order_id, person_id, count, price ) VALUES (?, ?, ?,?, ?)";
                PreparedStatement stmtItem = con.prepareStatement(sqlItem);
                for (CarOrderItem coi : order.getItems()) {
                    coi.setCarOrder(order);
                    addCarOrderItem(coi, stmtItem);
                }
                stmtItem.close();

                con.commit();
                return savedId;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
            throw s;
        }

    }

    private void addCarOrderItem(CarOrderItem orderItem, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, orderItem.getCar().getCarId());
        stmt.setLong(2, orderItem.getCarOrder().getCarOrderId());
        stmt.setLong(3, orderItem.getPerson().getPersonId());
        stmt.setInt(4, orderItem.getCount());
        stmt.setDouble(5, orderItem.getPrice());
        stmt.executeUpdate();
    }

    @Override
    public void updateCarOrder(CarOrder order) throws CarOrderDaoException {
        try {
            Connection con = getConnection();
            con.setAutoCommit(false);
            try {
                String sql = "DELETE all FROM  car_order_items WHERE car_order_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, order.getCarOrderId());
                String sql2 = "UPDATE car_order SET person_id=?, date_of_order = ? WHERE car_order_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sql2);
                stmt.setLong(1, order.getCarOrderId());
                stmt2.executeUpdate();
                List<CarOrderItem> listItems = order.getItems();
                Iterator iterator = listItems.iterator();

                while (iterator.hasNext()) {
                    CarOrderItem item = (CarOrderItem) iterator.next();
                    item.setCarOrder(order);

                    CarOrderItemManager.getInstance().addOrderItem(item);
                }
                con.commit();
                stmt.close();
                stmt2.close();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
            throw s;
        }
    }

    @Override
    public CarOrder getCarOrder(long orderId) throws CarOrderDaoException {
        try {
            Connection con = getConnection();
            con.setAutoCommit(false);
            try {
                String sql = "SELECT * FROM car_order WHERE car_order_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, orderId);
                ResultSet rs = stmt.executeQuery();
                CarOrder carOrder = null;
                while (rs.next()) {
                    carOrder = new CarOrder();
                    PersonDaoFirst dao = new PersonDaoFirst();
                    carOrder.setPerson(dao.getPerson(rs.getLong("person_id")));
                    carOrder.setDateOfOrder(rs.getDate("date_of_order"));
                }
                rs.close();
                stmt.close();

                carOrder.setItems(CarOrderItemManager.getInstance().findOrderItems(orderId));
                con.commit();
                return carOrder;
            } catch (Exception e) {
                e.printStackTrace();
                CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
            throw s;
        }

    }

    public List<CarOrder> findOrders(Long orderId) throws CarOrderDaoException {
        List<CarOrder> orders = new ArrayList<>();

        try {
            Connection con = getConnection();

            try {

                String sql = "SELECT * FROM car_order WHERE person_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, orderId);
                ResultSet rs = stmt.executeQuery();
                CarOrder carOrder = null;
                while (rs.next()) {
                    carOrder = new CarOrder();
                    PersonDaoFirst dao = new PersonDaoFirst();
                    carOrder.setPerson(dao.getPerson(rs.getLong("person_id")));
                    carOrder.setDateOfOrder(rs.getDate("date_of_order"));
                    carOrder.setCarOrderId(rs.getLong(1));
                }
                rs.close();
                stmt.close();

                orders.add(carOrder);


                return orders;
            } catch (Exception e) {
                e.printStackTrace();
                CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarOrderDaoException s = new CarOrderDaoException(e.getMessage(), 11);
            throw s;
        }

    }
}
