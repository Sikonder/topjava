package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {

        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.contentJson(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));

    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between/" + "2015-05-30T00:15:30" + "/" + "2015-05-30T23:15:30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MealTestData.contentJson(MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MealTestData.MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.contentJson(MealTestData.MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MealTestData.MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        MealTestData.assertMatch(mealService.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = new Meal(null, of(2015, Month.MAY, 29, 10, 0), "Завтрак Ожидаемый", 500);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = TestUtil.readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(mealService.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1, expected);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("UpdatedName");

        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(mealService.get(MEAL1_ID, USER_ID), updated);
    }
}
