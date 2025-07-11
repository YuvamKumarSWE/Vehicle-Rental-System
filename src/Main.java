import com.rentals.*;
import com.user.*;
import com.services.*;

public class Main {
    public static void main(String[] args) {

        RentalService service = new RentalService();

        Car car1 = new Car(102, "Toyota", "Camry", 70, false);
        TwoWheeler bike1 = new TwoWheeler(26, "Yamaha", "R1", 140, 1200 );
        Truck truck1 = new Truck(1004, "Volvo", "X-50", 300, 2000);

        service.addRentals(car1);
        service.addRentals(bike1);
        service.addRentals(truck1);

        User user = new User( 01,"Yuvam", 21);
        User user2 = new User( 02,"Krish", 21);



        service.rent(user, 1004, 5);
        service.rent(user2, 26, 2);

        service.printMap();




    }
}