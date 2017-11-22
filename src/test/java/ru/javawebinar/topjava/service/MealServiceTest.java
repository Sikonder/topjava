package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.mealsUserOne;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = new Meal(100004, LocalDateTime.of(2017, 11, 21, 20, 00, 00), "Ужин", 500);
        Meal test = service.get(100004, START_SEQ);
        assertEquals(meal, test);

    }

    @Test
    public void delete() throws Exception {
        service.delete(100004, START_SEQ);
        List<Meal> test = service.getAll(START_SEQ);
        assertThat(test.size(), is(2));
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> test = service.getAll(START_SEQ);
        Collections.reverse(mealsUserOne);
        assertThat(test, is(mealsUserOne));
    }

    @Test
    public void update() throws Exception {
        service.update(new Meal(100004, LocalDateTime.of(2017, 11, 21, 20, 00, 00), "Новый Ужин", 500), START_SEQ);
        List<Meal> test = service.getAll(START_SEQ);
        assertThat(test, hasItems(new Meal(100004, LocalDateTime.of(2017, 11, 21, 20, 00, 00), "Новый Ужин", 500)));
    }

    @Test
    public void create() throws Exception {
        service.create(new Meal(LocalDateTime.of(2017, 11, 22, 20, 00, 00), "Новый Ужин", 500), START_SEQ);
        List<Meal> test = service.getAll(START_SEQ);
        assertThat(test, hasItems(new Meal(100004, LocalDateTime.of(2017, 11, 22, 20, 00, 00), "Новый Ужин", 500)));
    }
}