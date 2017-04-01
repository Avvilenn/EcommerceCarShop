package com.carShop.web;

import com.carShop.business.PersonManager;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class PersonListServlet extends HttpServlet{
    private List <Person> personList;
    PersonManager manager;
    String str;

    @Override
    public void init() throws ServletException {
        super.init();
        manager = PersonManager.getInstance();
        str = "This is Person List Servlet. It's works!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            personList = manager.findPerson();

        } catch (CarBusinessException e) {
            e.printStackTrace();
        }
        req.setAttribute("Persons", personList);
        req.setAttribute("str", str);
            getServletContext().getRequestDispatcher("/persons.jsp").forward(req, resp);
    }

}
