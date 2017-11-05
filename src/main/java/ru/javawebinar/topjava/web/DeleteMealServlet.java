package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Dao.MealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet{
    MealDao mealDao = new MealDao();
    private static final Logger log = getLogger(DeleteMealServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("mealId"));
        mealDao.deleteMeal(id);

        log.info("Meal with id:"+id+" was deleted");

        resp.sendRedirect("meals");
    }
}
