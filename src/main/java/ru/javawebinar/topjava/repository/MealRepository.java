package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal);

    boolean delete(int id,int userId);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getFilteredByDate(int userId, LocalDate start, LocalDate end);
    Collection<Meal> getFilteredByTime(int userId, LocalTime start, LocalTime end);
}
