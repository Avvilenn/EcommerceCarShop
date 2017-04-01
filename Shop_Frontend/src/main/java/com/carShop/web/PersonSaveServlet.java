package com.carShop.web;

import com.carShop.business.PersonManager;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PersonSaveServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String personId = req.getParameter("personId");
        String nickName = req.getParameter("nick_name");
        String realName = req.getParameter("real_name");
        String password = req.getParameter("password");

        Person person = new Person(nickName, realName, password);
        person.setPersonId(Long.parseLong(personId));
        try {
            if(personId == null || personId.trim().isEmpty()) {

                PersonManager.getInstance().addPerson(person);
            } else {
                PersonManager.getInstance().updatePerson(person);
                            }
            resp.sendRedirect(req.getContextPath() + "/persons");
        } catch (CarBusinessException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
