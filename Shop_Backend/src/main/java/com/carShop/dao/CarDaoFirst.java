package com.carShop.dao;


import com.carShop.entity.Car;
import com.carShop.exception.CarDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoFirst implements CarDao {
    private Connection getConnection() throws Exception {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long addCar(Car car) throws CarDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "INSERT INTO cars (model, color, type, in_stock, price, year_of_production ) VALUES (?, ?, ?,?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, new String[]{
                        "person_id"
                });
                stmt.setString(1, car.getModel());
                stmt.setString(2, car.getColor());
                stmt.setString(3, car.getType());
                stmt.setBoolean(4, car.isInStock());
                stmt.setInt(5, car.getPrice());
                stmt.setInt(6, car.getYearOfProduction());
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
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }

    }

    @Override
    public void updateCar(Car car) throws CarDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "UPDATE cars SET model = ?, color= ?, type= ?, in_stock= ?, price= ?, year_of_production= ? WHERE car_id = ? ";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, car.getModel());
                stmt.setString(2, car.getColor());
                stmt.setString(3, car.getType());
                stmt.setBoolean(4, car.isInStock());
                stmt.setInt(5, car.getPrice());
                stmt.setInt(6, car.getYearOfProduction());
                stmt.setLong(7, car.getCarId());
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }
    }

    @Override
    public void deleteCar(Long carId) throws CarDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "DELETE FROM cars WHERE car_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, carId);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }
    }

    @Override
    public Car getCar(Long carId) throws CarDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "SELECT * FROM cars WHERE car_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, carId);
                ResultSet rs = stmt.executeQuery();
                Car car = null;
                while (rs.next()) {
                    car = new Car();
                    car.setCarId(rs.getLong("car_id"));
                    car.setModel(rs.getString("model"));
                    car.setType(rs.getString("type"));
                    car.setColor(rs.getString("color"));
                    car.setInStock(rs.getBoolean("in_stock"));
                    car.setPrice(rs.getInt("price"));
                    car.setYearOfProduction(rs.getInt("year_of_production"));
                }
                return car;

            } catch (SQLException e) {
                e.printStackTrace();
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }
    }

    @Override
    public List<Car> findCars(List<Long> carId) throws CarDaoException {
        List<Car> carList = new ArrayList<>();
        carId = new ArrayList<>();
        try {
            Connection con = getConnection();
            try {
                String in = "";
                for (Long l : carId) {
                    in += in.isEmpty() ? "" + l : "," + l;
                }
                in = "(" + in + ")";
                String sql = "SELECT * FROM cars WHERE car_id in " + in;
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Car car = new Car();
                    car.setCarId(rs.getLong("car_id"));
                    car.setModel(rs.getString("model"));
                    car.setType(rs.getString("type"));
                    car.setColor(rs.getString("color"));
                    car.setInStock(rs.getBoolean("in_stock"));
                    car.setPrice(rs.getInt("price"));
                    car.setYearOfProduction(rs.getInt("year_of_production"));
                    carList.add(car);
                }
                rs.close();
                stmt.close();
                return carList;
            } catch (SQLException e) {
                e.printStackTrace();
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }
    }

    @Override
    public List<Car> findCars() throws CarDaoException {
        List<Car> carList = new ArrayList<>();
        try {
            Connection con = getConnection();
            try {
                String sql = "SELECT * FROM cars";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Car car = new Car();
                    car.setCarId(rs.getLong("car_id"));
                    car.setModel(rs.getString("model"));
                    car.setType(rs.getString("type"));
                    car.setColor(rs.getString("color"));
                    car.setInStock(rs.getBoolean("in_stock"));
                    car.setPrice(rs.getInt("price"));
                    car.setYearOfProduction(rs.getInt("year_of_production"));
                    carList.add(car);
                }
                rs.close();
                stmt.close();
                return carList;
            } catch (SQLException e) {
                e.printStackTrace();
                CarDaoException s = new CarDaoException(e.getMessage(), 12);
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CarDaoException s = new CarDaoException(e.getMessage(), 12);
            throw s;
        }
    }

}
