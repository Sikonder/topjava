package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        Meal result = repository.get(id);
        if(result.getUserId().equals(userId)){
            repository.remove(id);
        }

    }

    @Override
    public Meal get(int id,int userId) {
        Meal result = repository.get(id);
        if(result.getUserId().equals(userId)){
            return result;
        }else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> result = repository.values().stream()
                .filter(x -> userId == x.getId())
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
}

