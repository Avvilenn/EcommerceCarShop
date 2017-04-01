package com.carShop.web;

import com.carShop.business.CarManager;
import com.carShop.business.PersonManager;
import com.carShop.entity.Car;
import com.carShop.entity.CarOrder;
import com.carShop.entity.CarOrderItem;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


public class CarOrderCreateServlet extends HttpServlet
{
    private PersonManager pm = PersonManager.getInstance();
    private CarManager cm = CarManager.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("car_id");
        HttpSession session = req.getSession();
        Long personId = (Long) session.getAttribute("PERSON_ID");
        if(personId == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        try {
            System.out.println("CarOrderCreateServlet.PERSON IS FOUND:" + personId);
            Person person = pm.getPerson(personId);
            CarOrder co = (CarOrder) session.getAttribute("CAR_ORDER");
            if(co == null) {
                co = new CarOrder();
                co.setPerson(person);
                co.setDateOfOrder(new Date());
            }
            Car car = cm.getCar(Long.parseLong(carId));
            CarOrderItem coi = new CarOrderItem();
            coi.setPerson(person);
            coi.setCount(1);
            coi.setPrice((double)car.getPrice());
            coi.setCar(car);
            co.addItem(coi);
            session.setAttribute("CAR_ORDER", co);
            System.out.println("CarOrderCreateServlet.Car is added:" + car.getCarId());
            System.out.println(co);
            resp.sendRedirect(req.getContextPath() + "/carOrderStatus");
        } catch(CarBusinessException ex) {
            ex.printStackTrace();
        }
    }
}


