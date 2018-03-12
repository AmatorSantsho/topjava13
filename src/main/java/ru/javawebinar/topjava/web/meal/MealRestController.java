package ru.javawebinar.topjava.web.meal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;
    protected final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    private int userId = AuthorizedUser.id();

    public List<Meal> getAll() {
        log.info("getAll");
        return (List<Meal>) service.getAll(userId);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id,userId);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id,userId);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public List<Meal> getFiltred(String startData, String endData, String startTime, String endTime) {
        log.info("getFiltred");
        return service.getFiltred(startData, endData, startTime, endTime,userId);
    }

}