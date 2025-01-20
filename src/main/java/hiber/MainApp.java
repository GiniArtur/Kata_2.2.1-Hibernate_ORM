package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User user = new User("User5", "Lastname5", "user5@mail.ru");
        Car car = new Car("MAzda", 2025);
        user.setCar(car);
        userService.add(user);
        userService.add(new Car("Toyota", 2015));
        userService.add(new Car("Toyota", 2016));
        userService.add(new Car("Toyota", 2023));
        userService.add(new Car("Toyota", 2018));
        List<User> users = userService.listUsers();
        List<Car> cars = userService.listCars();
        for (User bodyes : users) {
            System.out.println("Id = " + bodyes.getId());
            System.out.println("First Name = " + bodyes.getFirstName());
            System.out.println("Last Name = " + bodyes.getLastName());
            System.out.println("Email = " + bodyes.getEmail());
            System.out.println();
        }
        System.out.println(cars);
        System.out.println(userService.getUserByCar("MAzda", 2025));
        context.close();
    }
}
