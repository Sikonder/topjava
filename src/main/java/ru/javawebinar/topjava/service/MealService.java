package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Meal create(Meal meal);

    void delete(int id,int userId);

    Meal get(int id, int userId);

    Collection<MealWithExceed> getAll(int userId);

    void update(Meal meal);

    Collection<MealWithExceed> getFilteredByDate(int userId, LocalDate start, LocalDate end);

    Collection<MealWithExceed> getFilteredByTime(int userId, LocalTime start, LocalTime end);
}