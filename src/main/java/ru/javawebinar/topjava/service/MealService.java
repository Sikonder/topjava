package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;

public interface MealService {
    Meal create(Meal meal);

    void delete(int id,int userId);

    Meal get(int id, int userId);

    Collection<MealWithExceed> getAll(int userId);

    void update(Meal meal);
}