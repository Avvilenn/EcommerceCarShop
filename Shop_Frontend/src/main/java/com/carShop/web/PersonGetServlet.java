package com.carShop.web;

import com.carShop.business.PersonManager;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PersonGetServlet extends HttpServlet
{
    PersonManager manager;
    @Override
    public void init() throws ServletException {
        super.init();
        manager = PersonManager.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String personId = req.getParameter("personId");
        if(personId != null && !personId.trim().isEmpty()) {
            try {
                Person person = manager.getPerson(Long.parseLong(personId));
                req.setAttribute("person", person);
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/personEdit.jsp").forward(req, resp);
    }
}
