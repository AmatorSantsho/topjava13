package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    {
        MealsUtil.MEALS.forEach(this::save);
    }



    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
         Meal meal=get(id,userId);
       return meal != null && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
Meal meal=repository.get(id);
        if (meal==null){
            return null;
        }else if (meal.getUserId()==userId){
            return meal;
        }else return null;
          }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> list = repository.values().stream().filter(meal -> userId == meal.getUserId())
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime())).collect(Collectors.toList());
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public List<Meal> getFiltred(String startData, String endData, String startTime, String endTime, int userId) {

        LocalTime startT = startTime.equals("") ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime endT = endTime.equals("") ? LocalTime.MAX : LocalTime.parse(endTime);
        LocalDate startD = startData.equals("") ? LocalDate.MIN : LocalDate.parse(startData);
        LocalDate endD = endData.equals("") ? LocalDate.MAX : LocalDate.parse(endData);
        List<Meal> list = getAll(userId).stream().filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startT, endT)).
                filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), startD, endD)).collect(Collectors.toList());

        return list;


    }
}

