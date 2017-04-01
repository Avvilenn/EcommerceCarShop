package com.carShop.web;

import com.carShop.business.CarManager;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CarGetServlet extends HttpServlet
{
    private CarManager manager;

    @Override
    public void init() throws ServletException {
        super.init();
        manager = CarManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("carId");
        if(carId != null && !carId.trim().isEmpty()) {
            try {
                Car car = manager.getCar(Long.parseLong(carId));
                req.setAttribute("car", car);
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/editCar.jsp").forward(req, resp);
    }
}
