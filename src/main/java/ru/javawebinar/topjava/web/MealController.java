package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class MealController {

    @Autowired
    MealService service;

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    public void filter(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        List<Meal> mealsDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, AuthorizedUser.id());

        List<MealWithExceed> result = MealsUtil.getFilteredWithExceeded(mealsDateFiltered,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );

        request.setAttribute("meals", result);
    }

    @PostMapping("")
    public String postDispatcher(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action", required = false) String action, HttpServletRequest request) {
        if (action != null) {
            if (action.equals("filter")) {
                filter(request);
            }
            return "meals";
        }

        int userId = AuthorizedUser.id();
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"))
                , request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            service.create(meal, userId);
        } else {
            meal.setId(id);
            service.update(meal, userId);
        }
        return "redirect:/meals";


    }

    @GetMapping(value = "", params = "action")
    public String addNew(@RequestParam String action, HttpServletRequest request) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping(value = "", params = {"action", "id"})
    public String getDispatcher(@RequestParam(value = "id") int id, @RequestParam String action, HttpServletRequest request) {
        if (action.equals("delete")) {
            service.delete(id, AuthorizedUser.id());
            return "redirect:/meals";
        } else if (action.equals("update")) {
            int userId = AuthorizedUser.id();
            Meal meal = service.get(id, userId);
            request.setAttribute("meal", meal);
            return "mealForm";
        } else {
            return "meals";
        }
    }

}
