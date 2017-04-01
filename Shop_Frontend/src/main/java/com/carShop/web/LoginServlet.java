package com.carShop.web;

import com.carShop.business.PersonManager;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet
{
    private PersonManager pm = PersonManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Person p = pm.getPerson(1L);
            if (p != null) {
                System.out.println("LoginServlet.PERSON IS PUT TO SESSION:" + p.getPersonId());
                req.getSession().setAttribute("PERSON_ID", p.getPersonId());
            }
            resp.sendRedirect(req.getContextPath() + "/cars");
        } catch (CarBusinessException e) {
            e.printStackTrace();
        }
    }
}
