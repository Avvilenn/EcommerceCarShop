package com.carShop.web;

import com.carShop.business.CarOrderManager;
import com.carShop.entity.CarOrder;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CarOrderSaveServlet extends HttpServlet
{
    private CarOrderManager manager;


    @Override
    public void init() throws ServletException {
        super.init();
        manager = CarOrderManager.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long personId = (Long) session.getAttribute("PERSON_ID");
        if(personId == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        CarOrder co = (CarOrder) session.getAttribute("CAR_ORDER");

        if(co != null) {
            try {
                manager.addCarOrder(co);
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/cars").forward(req, resp);
            session.removeAttribute("COR_ORDER");
        } else {
            System.out.println("CarOrderStatusServlet.CAR_ORDER is NOT FOUND");
            resp.sendRedirect(req.getContextPath() + "/cars");
        }
    }
}
