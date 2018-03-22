package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by 123 on 21.03.2018.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID1, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID2, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL1, MEAL3);
    }


    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<Meal> meals = service.getBetweenDateTimes(STARTTIME, ENDTIME, USER_ID);
        assertMatch(meals, MEAL2);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Meal> list = service.getAll(USER_ID);
        assertMatch(list, MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal mealUpdated = new Meal(MEAL3);
        mealUpdated.setDescription("UpdatedName");
        mealUpdated.setCalories(333);
        service.update(mealUpdated, USER_ID);
        assertMatch(service.get(MEAL_ID3, USER_ID), mealUpdated);
    }

    @Test(expected = NotFoundException.class)
    public void testGetOther() throws Exception {
        service.get(MEAL_ID_OTHER, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOther() throws Exception {
        service.delete(MEAL_ID_OTHER, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateOther() throws Exception {
        Meal mealUpdated = new Meal(MEAL_OTHER);
        mealUpdated.setDescription("UpdatedName");
        mealUpdated.setCalories(333);
        service.update(mealUpdated, USER_ID);
        service.get(mealUpdated.getId(), USER_ID);
    }

    @Test
    public void testCreate() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "newMeal", 666);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newMeal, MEAL1, MEAL2, MEAL3);
    }
}