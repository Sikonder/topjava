package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.Dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditMealServlet extends HttpServlet{
    MealDao mealDao = new MealDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("mealDate"));
        String description = req.getParameter("mealDesc");
        int calories = Integer.parseInt(req.getParameter("mealCal"));
        int id = Integer.parseInt(req.getParameter("mealId"));


        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("id",id);
        req.setAttribute("date", dateTime);
        req.setAttribute("description",description);
        req.setAttribute("calories",calories);
        req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        int id = Integer.parseInt(req.getParameter("id"));
        mealDao.deleteMeal(id);

        Meal meal = new Meal(dateTime,description,calories);
        meal.setId(id);

        mealDao.addMeal(meal);

        resp.sendRedirect("meals");
    }
}
