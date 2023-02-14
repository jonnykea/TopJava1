package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            MealRestController mealController = appCtx.getBean(MealRestController.class);

            Meal extraMeal = new Meal(LocalDateTime.now(), "lunch", 2000);

            System.out.println("\n");
            mealController.getAll().forEach(System.out::println);
            mealController.getFilteredByDates(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), LocalDateTime.now()).forEach(System.out::println);
            mealController.delete(1);
            mealController.getAll().forEach(System.out::println);
            mealController.create(extraMeal);
            mealController.getAll().forEach(System.out::println);

            String id = "8";
            System.out.println(Integer.valueOf(id));
        }
    }
}