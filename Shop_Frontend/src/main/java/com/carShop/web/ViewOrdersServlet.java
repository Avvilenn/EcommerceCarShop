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
import java.util.ArrayList;
import java.util.List;


public class ViewOrdersServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarOrder> orders = new ArrayList<>();
        HttpSession session = req.getSession();
        Long personId = (Long) session.getAttribute("PERSON_ID");
        CarOrderManager manager = CarOrderManager.getInstance();
        try {

            orders = manager.findOrderItems(personId);

        } catch (CarBusinessException e) {
            e.printStackTrace();
        }

        req.setAttribute("ORDERS", orders);
            getServletContext().getRequestDispatcher("/viewOrders.jsp").forward(req, resp);

    }
}
