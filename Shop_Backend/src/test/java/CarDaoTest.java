package java;




import com.carShop.business.CarManager;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarDaoTest
{
    public static List<Car> carsList;
    public static CarManager manager;
    static {
        carsList = new ArrayList<Car>();
        manager = CarManager.getInstance();
    }

    @BeforeClass
    public static void createPerson (){
        for (int i=0; i< 10; i++){
            Car car = new Car();
            car.setModel("model" + i);
            car.setColor("color " + i);
            car.setPrice(i*1000);
            car.setType("type"+i);
            car.setYearOfProduction(2010+i);
            car.setInStock(true);
            carsList.add(car);
        }
    }

    @Test
    public void testAddPerson (){
        for (int i = 0; i < carsList.size(); i++){
            try {
                System.out.println("car was added to database, new id = " + manager.addCar(carsList.get(i)));
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testUpdatePerson (){
        for (int i = 0; i < carsList.size(); i++){
            try {
                Car car = carsList.get(i);
                Long id = manager.addCar(car);
                manager.updateCar(car);
                System.out.println( "person " + car + "was updated " + id);

            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testgetPerson (){
        for (int i = 0; i < carsList.size(); i++){
            try {
                Long id = Long.parseLong(String.valueOf(i));
                Car car = manager.getCar(id);
                System.out.println("get car " + car + "from table");
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetLIstPersons ()  {
        List <Long> ids = new ArrayList<Long>();
        for (int i = 0; i < carsList.size(); i++){
            try {
                Long personId = manager.addCar(carsList.get(i));
                ids.add(personId);
            } catch (CarBusinessException e) {
                e.printStackTrace();
            }
        }
        try {
            List <Car> gotPersons = manager.findCars(ids);
            for ( Car car : gotPersons) {
                System.out.println(car);
            }

        } catch (CarBusinessException e) {
            e.printStackTrace();
        }

    }
}
