package ru.javawebinar.topjava.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 123 on 27.04.2018.
 */
@RestController
@RequestMapping("ajax/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }


    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                              @RequestParam("dateTime")String dateTime,
                              @RequestParam("description") String description,
                              @RequestParam("calories") Integer calories){


        Meal meal=new Meal(id,LocalDateTime.parse(dateTime),description,calories);
        if (meal.isNew()){
            super.create(meal);
        }

    }
}
