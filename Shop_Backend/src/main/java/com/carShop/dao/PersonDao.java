package com.carShop.dao;;


import com.carShop.entity.Person;
import com.carShop.exception.PersonDaoException;
import java.util.List;


public interface PersonDao {

    Long addPerson(Person person)throws PersonDaoException;
    void updatePerson(Person person)throws PersonDaoException;
    Person getPerson(Long personId)throws PersonDaoException;
    List<Person> findPersons(List<Long> personId)throws PersonDaoException;
    List<Person> findPersons()throws PersonDaoException;
}
