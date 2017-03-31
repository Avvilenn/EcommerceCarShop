package java;

import com.carShop.business.CarOrderItemManager;
import com.carShop.entity.Car;
import com.carShop.entity.CarOrderItem;
import com.carShop.entity.Person;
import com.carShop.exception.CarBusinessException;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class CarOrderItemTest {
    public static List<CarOrderItem> itemsList;
    public static CarOrderItemManager manager;

    static {
        itemsList = new ArrayList<>();
        manager = CarOrderItemManager.getInstance();
    }


    @BeforeClass
    public static void createPerson() {
        for (int i = 0; i < 10; i++) {
            CarOrderItem item = new CarOrderItem();
            Person person = new Person();
            person.setPassword("password" + i);
            person.setNickName("nick name" + i);
            Car car = new Car();
            car.setModel("model" + i);
            car.setColor("color " + i);
            car.setPrice(i * 1000);
            car.setType("type" + i);
            car.setYearOfProduction(2010 + i);
            car.setInStock(true);
            item.setCount(i + i);
            item.setCar(car);
            item.setPerson(person);
            item.setPrice(Double.parseDouble(String.valueOf(item.getCar().getPrice())));
            itemsList.add(item);
        }

    }

    @Test
    public void testAddPerson() {
        for (int i = 0; i < itemsList.size(); i++) {

            try {
                System.out.println("item was added to database, new id = " + manager.addOrderItem(itemsList.get(i)));
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testUpdatePerson() {
        for (int i = 0; i < itemsList.size(); i++) {
            try {
                CarOrderItem item = itemsList.get(i);
                Long id = manager.addOrderItem(item);
                item.setCarOrderItemId(id);
                manager.updateOrderItem(item);
                System.out.println("item " + item + "was updated " + id);

            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testgetPerson() {
        for (int i = 0; i < itemsList.size(); i++) {
            try {
                Long id = Long.parseLong(String.valueOf(i));
                CarOrderItem item = manager.getCarOrderItem(id);
                System.out.println("get order item " + item + "from table");
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetLIstPersons() {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < itemsList.size(); i++) {
            try {
                Long itemId = manager.addOrderItem(itemsList.get(i));
                List<CarOrderItem> gotItem = manager.findOrderItems(itemId);
                for (CarOrderItem item : gotItem) {
                    System.out.println(item);
                }

            } catch (CarBusinessException e) {
                e.printStackTrace();
            }

        }
    }
}
