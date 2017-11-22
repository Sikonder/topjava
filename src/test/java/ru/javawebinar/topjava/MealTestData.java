package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final List<Meal> mealsUserOne = new ArrayList<>(Arrays.asList(
            new Meal(100002, LocalDateTime.of(2017, 11, 21, 10, 00, 00), "Завтрак", 500),
            new Meal(100003, LocalDateTime.of(2017, 11, 21, 13, 00, 00), "Обед", 700),
            new Meal(100004, LocalDateTime.of(2017, 11, 21, 20, 00, 00), "Ужин", 500)
    ));
}
