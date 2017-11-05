package ru.javawebinar.topjava.Dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealDao {
    public void addMeal(Meal meal) {
        MealsUtil.meals.add(meal);
    }

    public void deleteMeal(int id) {
        Meal mealToDelete = null;
        for (Meal meal : MealsUtil.meals) {
            if (meal.getId() == id) {
                mealToDelete = meal;
            }
        }
        MealsUtil.meals.remove(mealToDelete);
    }
}
