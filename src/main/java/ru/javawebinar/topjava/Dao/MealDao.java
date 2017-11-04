package ru.javawebinar.topjava.Dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealDao {
    public void addMeal(Meal meal){
        MealsUtil.meals.add(meal);
    }
    public void deleteMeal(int id){
        MealsUtil.meals.remove(id);
    }
    public void editMeal(){
        //TODO
    }
}
