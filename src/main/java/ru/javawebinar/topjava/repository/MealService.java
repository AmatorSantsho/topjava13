package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by 123 on 05.03.2018.
 */
public interface MealService {
    public void add(Meal meal);
    public void delete(int mealId);
    public void update(Meal meal);
    public Meal getById(int mealId);
    public List<Meal> getAll();
}
