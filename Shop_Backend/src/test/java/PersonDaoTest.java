package java;


import com.carShop.business.PersonManager;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoTest
{

    public static List<Person> personList;
    public static PersonManager manager;

    static {
        personList = new ArrayList<>();
        manager = PersonManager.getInstance();
    }


    @BeforeClass
    public static void createPerson() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setPassword("password" + i);
            person.setNickName("nick name" + i);
            personList.add(person);
        }

    }

    @Test
    public void testAddPerson() throws Exception {
        for (int i = 0; i < personList.size(); i++) {
            System.out.println("person was added to database, new id = " + manager.addPerson(personList.get(i)));
        }
    }

    @Test
    public void testUpdatePerson() {
        for (int i = 0; i < personList.size(); i++) {
            try {
                Person person = personList.get(i);
                Long id = manager.addPerson(person);
                manager.updatePerson(person);
                System.out.println("person " + person + "was updated " + id);

            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }

    }

   @Test
    public void testgetPerson() {
        for (int i = 0; i < personList.size(); i++) {
            try {
                Long id = Long.parseLong(String.valueOf(i));
                Person person = manager.getPerson(id);
                System.out.println("get person " + person + "from table");
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
    }

  @Test
    public void testGetLIstPersons() {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            try {
                Long personId = manager.addPerson(personList.get(i));
                ids.add(personId);
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
        try {
            List<Person> gotPersons = manager.findPerson(ids);
            for (Person person : gotPersons) {
                System.out.println(person);
            }

        } catch (CarBusinessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetList() {


        try {
            List<Person> gotPersons = manager.findPerson();
            for (Person person : gotPersons) {
                System.out.println(person);
            }

        } catch (CarBusinessException e) {
            e.printStackTrace();
        }

    }


}
