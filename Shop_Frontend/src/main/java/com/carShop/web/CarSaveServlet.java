package com.carShop.web;

import com.carShop.business.CarManager;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CarSaveServlet extends HttpServlet
{
    private CarManager manager;

    @Override
    public void init() throws ServletException {
        super.init();
        manager = CarManager.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("carId");
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        String type = req.getParameter("type");
        String price = req.getParameter("price");
        String year = req.getParameter("year");

        Car car = new Car(model, type, color, true, Integer.parseInt(price), Integer.parseInt(year));
        try {
            if(carId == null || carId.trim().isEmpty()) {
                manager.addCar(car);
            } else {
                car.setCarId(Long.parseLong(carId));
                manager.updateCar(car);
            }
            resp.sendRedirect(req.getContextPath() + "/cars");
        } catch (CarBusinessException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
