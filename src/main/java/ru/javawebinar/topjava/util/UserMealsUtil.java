package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            map.put(localDate, map.getOrDefault(localDate, 0) + userMeal.getCalories());
        }
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceed = false;
                LocalDate localDate = userMeal.getDateTime().toLocalDate();
                if (map.get(localDate) > caloriesPerDay) {
                    exceed = true;
                }
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed);
                list.add(userMealWithExceed);
            }
        }
        return list;
    }
    public static List<UserMealWithExceed> getFilteredWithExceededWithStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> map =mealList.stream().collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(),Collectors.summingInt(UserMeal::getCalories)));
        mealList.stream().filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().
                toLocalTime(),startTime,endTime)).map(userMeal1 -> new UserMealWithExceed(userMeal1.getDateTime(),userMeal1.getDescription(),userMeal1.getCalories(),
                map.get(userMeal1.getDateTime().toLocalDate())>caloriesPerDay)).collect(Collectors.toList());
        return list;
    }
}
