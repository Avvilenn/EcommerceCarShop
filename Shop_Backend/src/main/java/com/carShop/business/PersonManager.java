package com.carShop.business;


import com.carShop.dao.GlobalDaoFactory;
import com.carShop.dao.PersonDao;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;
import com.carShop.exception.PersonDaoException;

import java.util.List;

public class PersonManager
{
    private PersonDao dao = GlobalDaoFactory.getPersonDao();

    private static PersonManager instance = new PersonManager();

    private PersonManager() {
    }

    public static PersonManager getInstance() {
        return instance;
    }

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }



    public Long addPerson(Person person) throws CarBusinessException {
        try {
            Long id = dao.addPerson(person);
            System.out.println("New user added, class PersonManager, method addPerson");
            return id;
        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 9);
        }

    }

    public Long updatePerson(Person person) throws CarBusinessException {
        try {
            dao.updatePerson(person);
            System.out.println("New user updated, class PersonManager, method updatePerson");
            return null;
        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 9);
        }

    }

    public Person getPerson(Long personId) throws CarBusinessException {
        try {
            return dao.getPerson(personId);
        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 9);
        }

    }

    public List<Person> findPerson(List<Long> personId)throws CarBusinessException {
        try {
            return dao.findPersons(personId);
        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 9);
        }

    }


    public List<Person> findPerson()throws CarBusinessException {
        try {
            return dao.findPersons();
        } catch (PersonDaoException e) {
            e.printStackTrace();
            throw new CarBusinessException(e.getMessage(), 9);
        }

    }

    public void changeStatus(Long personId, boolean active) {
    }
}
