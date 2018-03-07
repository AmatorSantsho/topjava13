package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryDB.Repository;

import java.util.List;

/**
 * Created by 123 on 05.03.2018.
 */
public class MealServiceImpl implements MealService{

    private Repository dataSourse = Repository.getInstance();

    @Override
    public void add(Meal meal) {
        dataSourse.addNewInDB(meal);
    }

    @Override
    public void delete(int mealId) {
        dataSourse.deleteFromDB(mealId);
    }

    @Override
    public void update(Meal meal) {
        dataSourse.updateInDB(meal);
    }

    @Override
    public Meal getById(int mealId) {
        return dataSourse.getByIdFromDB(mealId);
    }

    @Override
    public List<Meal> getAll() {
        return dataSourse.getAllFromDB();
    }
}
