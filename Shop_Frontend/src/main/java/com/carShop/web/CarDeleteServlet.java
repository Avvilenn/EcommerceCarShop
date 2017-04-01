package com.carShop.web;

import com.carShop.business.CarManager;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CarDeleteServlet extends HttpServlet
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
        try {
            manager.deleteCar(Long.parseLong(carId));
        } catch (CarBusinessException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
