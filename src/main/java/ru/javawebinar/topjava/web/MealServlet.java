package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealService;
import ru.javawebinar.topjava.repository.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;
/**
 * Created by 123 on 06.03.2018.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log=getLogger(MealServlet.class);
    private MealService mealDao = new MealServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String dataAndTime = req.getParameter("data");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dataAndTime, format);
        String s = req.getParameter("description");
        int c = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(localDateTime, s, c);
        if (req.getParameter("submit").equals("Add Meal"))
            mealDao.add(meal);
        else {
            int id = Integer.parseInt(req.getParameter("id"));
            meal.setId(id);
            mealDao.update(meal);
        }
        resp.sendRedirect("/topjava/meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String p1 = null;
        Integer p2 = 0;
        if (request.getParameter("param1") != null) {
            p1 = request.getParameter("param1");
            p2 = Integer.parseInt(request.getParameter("param2"));
        }
        if (p1 != null && p1.equals("edit")) {
            Meal mealToEdit = mealDao.getById(p2);
            request.setAttribute("mealToEdit", mealToEdit);
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(mealDao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            request.setAttribute("list", mealsWithExceeded);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals.jsp");
            requestDispatcher.forward(request, response);
        } else if (p1 != null && p1.equals("delete")) {
            mealDao.delete(Integer.parseInt(request.getParameter("param2")));
            response.sendRedirect("/topjava/meals");
        } else {
            List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(mealDao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            request.setAttribute("list", mealsWithExceeded);
            Meal meal = new Meal(null, null, 0);
            request.setAttribute("mealToEdit", meal);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}