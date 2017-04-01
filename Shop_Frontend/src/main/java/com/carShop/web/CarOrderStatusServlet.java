package com.carShop.web;

import com.carShop.entity.CarOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CarOrderStatusServlet extends HttpServlet
{
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
            System.out.println("CarOrderStatusServlet.CAR_ORDER is FOUND");
            req.setAttribute("CAR_ORDER_REQ", co);
            getServletContext().getRequestDispatcher("/car_order.jsp").forward(req, resp);
        } else {
            System.out.println("CarOrderStatusServlet.CAR_ORDER is NOT FOUND");
            resp.sendRedirect(req.getContextPath() + "/cars");
        }
    }
}
