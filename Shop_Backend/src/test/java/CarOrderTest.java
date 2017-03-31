package java;


import com.carShop.business.CarOrderManager;
import com.carShop.entity.Car;
import com.carShop.entity.CarOrder;
import com.carShop.entity.CarOrderItem;
import com.carShop.entity.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CarOrderTest
{
    @Test
    public void testAddCarOrder() throws Exception {
        CarOrderManager orderManager = CarOrderManager.getInstance();

        CarOrder carOrder = new CarOrder();
        Person person = new Person();
        person.setPersonId(1L);
        Car car = new Car();
        car.setCarId(1L);
        carOrder.setPerson(person);
        carOrder.setDateOfOrder(new Date());

        List<CarOrderItem> itemsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CarOrderItem item = new CarOrderItem();
            item.setCount(i + i);
            item.setCar(car);
            item.setPerson(person);
            item.setPrice(99.9);
            item.setCarOrder(carOrder);
            itemsList.add(item);
        }
        carOrder.setItems(itemsList);

        orderManager.addCarOrder(carOrder);
    }

}
