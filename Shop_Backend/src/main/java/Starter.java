import com.carShop.business.CarManager;
import com.carShop.entity.Car;
import com.carShop.exception.CarBusinessException;

import java.util.List;

public class Starter {
    public static void main(String[] args) throws CarBusinessException {
        CarManager manager = CarManager.getInstance();
        List<Car> carlist = manager.findCars();
        System.out.println(carlist);
    }
}
