package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user1 = new User("User1", "LastName1", "user1@mail.ru");
        User user2 = new User("User2", "LastName2", "user2@mail.ru");
        User user3 = new User("User3", "LastName3", "user3@mail.ru");
        User user4 = new User("User4", "LastName4", "user4@mail.ru");
        User user5 = new User("User5", "LastName5", "user5@mail.ru");
        Car car1 = new Car("Lada", 2023);
        Car car2 = new Car("BMW", 2023);
        Car car3 = new Car("Audi", 2023);
        Car car4 = new Car("Mercedes", 2023);
        Car car5 = new Car("Ford", 2023);
        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));
        List<User> users = userService.listUsers();
        for (User bodyes : users) {
            System.out.println("Id = " + bodyes.getId());
            System.out.println("First Name = " + bodyes.getFirstName());
            System.out.println("Last Name = " + bodyes.getLastName());
            System.out.println("Email = " + bodyes.getEmail());
            System.out.println("Car = " + bodyes.getCar());
            System.out.println();
        }
        // Найти юзера с данной машиной
        System.out.println(userService.getUserByCar("BMW", 2023));
        // ЕСли нет такой машины!!!!
        try {
            User notFoundUser = userService.getUserByCar("GAZ", 4211);
        } catch (NoResultException e) {
            e.printStackTrace();
            System.out.println("User not found");
        }
        context.close();
    }
}
