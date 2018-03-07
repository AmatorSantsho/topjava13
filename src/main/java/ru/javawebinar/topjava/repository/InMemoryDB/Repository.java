package ru.javawebinar.topjava.repository.InMemoryDB;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 123 on 05.03.2018.
 */
public class Repository {
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private AtomicInteger count=new AtomicInteger(6);

    private ConcurrentHashMap<Integer, Meal> hashMap = new ConcurrentHashMap<>();

    private Repository() {
        Meal meal1 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        meal1.setId(1);
        hashMap.put(1, meal1);
        Meal meal2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        meal2.setId(2);
        hashMap.put(2, meal2);
        Meal meal3 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        meal3.setId(3);
        hashMap.put(3, meal3);
        Meal meal4 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        meal4.setId(4);
        hashMap.put(4, meal4);
        Meal meal5 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        hashMap.put(5, meal5);
        meal5.setId(5);
        Meal meal6 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        meal6.setId(6);
        hashMap.put(6, meal6);

    }


    public List<Meal> getAllFromDB() {
        List<Meal> list = new ArrayList<>();
        for (Meal meal : hashMap.values()) {
            list.add(meal);
        }
        return list;
    }

    public void addNewInDB(Meal meal) {

        meal.setId(count.incrementAndGet());
        hashMap.put(count.intValue(), meal);
    }



    public void deleteFromDB(int mealId) {
        hashMap.remove(mealId);
    }

    public Meal getByIdFromDB(int id) {
        return hashMap.get(id);
    }

    public synchronized void updateInDB(Meal meal) {
        hashMap.remove(meal.getId());
        hashMap.put(meal.getId(), meal);
    }
}
