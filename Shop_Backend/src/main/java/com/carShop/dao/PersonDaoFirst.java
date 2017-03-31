package com.carShop.dao;


import com.carShop.entity.Person;
import com.carShop.exception.PersonDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoFirst implements PersonDao
{


    private Connection getConnection() throws Exception {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long addPerson(Person person) throws PersonDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "INSERT INTO persons (nick_name, password ) VALUES (?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, new String[]{
                        "person_id"
                });
                stmt.setString(1, person.getNickName());
                stmt.setString(2, person.getPassword());
                stmt.executeUpdate();
                ResultSet gk = stmt.getGeneratedKeys();
                Long savedId = 0L;
                if (gk.next()) {
                    savedId = gk.getLong(1);
                }
                gk.close();
                stmt.close();
                return savedId;
            } catch (Exception ex) {
                PersonDaoException s = new PersonDaoException(9, ex.getMessage());
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            PersonDaoException s = new PersonDaoException(9, ex.getMessage());
            throw s;
        }
    }


    @Override
    public void updatePerson(Person person) throws PersonDaoException {
        try {
            Connection con = getConnection();
            try {
                String sql = "UPDATE persons SET nick_name = ?, password= ? WHERE person_id = ? ";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, person.getNickName());
                stmt.setString(2, person.getPassword());
                stmt.setLong(3, person.getPersonId());
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception ex) {
                PersonDaoException s = new PersonDaoException(9, ex.getMessage());
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error: " + ex.getMessage());
        }

    }

    @Override
    public Person getPerson(Long personId) throws PersonDaoException {
        try {
            System.out.println("PersonDaoFirst.getPerson:" + personId);
            Connection con = getConnection();
            try {
                String sql = "SELECT * FROM persons WHERE person_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, personId);
                ResultSet rs = stmt.executeQuery();
                Person person = null;
                if (rs.next()) {
                    person = new Person();
                    person.setPersonId(rs.getLong("person_id"));
                    person.setNickName(rs.getString("nick_name"));
                    person.setPassword(rs.getString("password"));
                    person.setPersonId(rs.getLong("person_id"));

                }
                rs.close();
                stmt.close();
                return person;

            } catch (SQLException e) {
                e.printStackTrace();
                PersonDaoException s = new PersonDaoException(9, e.getMessage());
                throw s;
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            PersonDaoException s = new PersonDaoException(9, e.getMessage());
            throw s;
        }

    }


    @Override
    public List<Person> findPersons(List<Long> personId) throws PersonDaoException {
        List<Person> personList = new ArrayList<>();
        personId = new ArrayList<>();
        try {
            Connection con = getConnection();
            try {
                String in = "";
                for (Long l : personId) {
                    in += in.isEmpty() ? "" + l : "," + l;
                }
                in = "(" + in + ")";
                    String sql = "SELECT * FROM persons WHERE person_id in " + in;
                    PreparedStatement stmt = con.prepareStatement(sql);

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Person person = new Person();
                        person.setNickName( rs.getString("nick_name"));
                        person.setPassword(rs.getString("password"));
                        person.setPersonId(rs.getLong("person_id"));
                        personList.add(person);
                    }
                    rs.close();
                    stmt.close();

                return personList;
            } catch (SQLException e) {
                e.printStackTrace();
                PersonDaoException s = new PersonDaoException(9, e.getMessage());
                throw s;
            } finally {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            PersonDaoException s = new PersonDaoException(9, e.getMessage());
            throw s;
        }

    }

    @Override
    public List<Person> findPersons() throws PersonDaoException {
        List<Person> personList = new ArrayList<>();

        try {
            Connection con = getConnection();
            try {
                   String sql = "SELECT * FROM persons ";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Person person = new Person();
                        person.setNickName( rs.getString("nick_name"));
                        person.setPassword(rs.getString("password"));
                        person.setPersonId(rs.getLong("person_id"));
                        person.setRealName(rs.getString("real_name"));
                        personList.add(person);
                    }
                    rs.close();
                    stmt.close();

                return personList;
            } catch (SQLException e) {
                e.printStackTrace();
                PersonDaoException s = new PersonDaoException(9, e.getMessage());
                throw s;
            } finally {
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            PersonDaoException s = new PersonDaoException(9, e.getMessage());
            throw s;
        }

    }
}
