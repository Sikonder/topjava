package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {

        User user = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(user);
            em.persist(meal);

        } else {
            Query query = em.createQuery("UPDATE Meal m SET m.description=:desc,m.calories=:cal,m.dateTime=:dt WHERE m.id=:id AND m.user.id=:userId");
            query.setParameter("desc", meal.getDescription())
                    .setParameter("cal", meal.getCalories())
                    .setParameter("dt", meal.getDateTime())
                    .setParameter("id", meal.getId())
                    .setParameter("userId", userId)
                    .executeUpdate();

        }
        Query query = em.createQuery("SELECT m FROM Meal  m WHERE m.user.id=:userId AND m.id=:id");
        List<Meal> meals = query.setParameter("userId", userId).setParameter("id", meal.getId()).getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createQuery("DELETE from Meal m WHERE m.id=:id AND m.user.id=:userId");
        return query.setParameter("id", id).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Query query = em.createQuery("SELECT m FROM Meal  m WHERE m.user.id=:userId AND m.id=:id");
        List<Meal> meals = query.setParameter("userId", userId).setParameter("id", id).getResultList();
        return DataAccessUtils.singleResult(meals);


    }

    @Override
    public List<Meal> getAll(int userId) {
        Query query = em.createQuery("SELECT m FROM Meal  m WHERE m.user.id=:userId ORDER BY m.dateTime DESC");
        return query.setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createQuery("SELECT m FROM Meal  m WHERE m.user.id=:userId ORDER BY m.dateTime DESC");
        List<Meal> meals = query.setParameter("userId", userId).getResultList();
        meals = meals.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .collect(Collectors.toList());
        return meals;
    }
}