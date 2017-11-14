package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Meal result = repository.get(id);
        if (result.getUserId().equals(userId)) {
            repository.remove(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);

        Meal result = repository.get(id);
        if (result.getUserId().equals(userId)) {
            return result;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        List<Meal> result = repository.values().stream()
                .filter(x -> userId == x.getUserId())
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(toList());
        Collections.reverse(result);

        return result;
    }

    @Override
    public Collection<Meal> getFilteredByDate(int userId, LocalDate start, LocalDate end) {
        log.info("getFilteredByDate");
        List<Meal> result = repository.values().stream()
                .filter(x -> userId == x.getUserId())
                .filter(x -> DateTimeUtil.isBetweenDate(x.getDateTime().toLocalDate(),start,end))
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(toList());
        Collections.reverse(result);

        return result;
    }

    @Override
    public Collection<Meal> getFilteredByTime(int userId, LocalTime start, LocalTime end) {
        log.info("getFilteredByDate");
        List<Meal> result = repository.values().stream()
                .filter(x -> userId == x.getUserId())
                .filter(x -> DateTimeUtil.isBetween(x.getDateTime().toLocalTime(),start,end))
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(toList());
        Collections.reverse(result);

        return result;
    }
}

