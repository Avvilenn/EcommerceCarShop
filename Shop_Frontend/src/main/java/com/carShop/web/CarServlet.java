package com.carShop.web;

import com.carShop.business.CarManager;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CarServlet extends HttpServlet
{
    private CarManager manager;

    @Override
    public void init() throws ServletException {
        super.init();
        manager = CarManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Car> list = manager.findCars();
            req.setAttribute("CARS", list);
            getServletContext().getRequestDispatcher("/carList.jsp").forward(req, resp);
        } catch (CarBusinessException e) {
            e.printStackTrace();
        }
    }

}
