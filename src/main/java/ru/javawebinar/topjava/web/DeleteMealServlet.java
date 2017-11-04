package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.Dao.MealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMealServlet extends HttpServlet{
    MealDao mealDao = new MealDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("mealId"));
        mealDao.deleteMeal(id);

        resp.sendRedirect("meals");
    }
}
