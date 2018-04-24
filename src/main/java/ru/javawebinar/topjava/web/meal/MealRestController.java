package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll(){
        return super.getAll();
    }

    @Override
    @GetMapping( value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id")int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping( value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable ("id")int id) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created=super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@RequestParam (value = "startDate", required = false) String startDate,
                                           @RequestParam (value = "endDate", required = false) String  endDate,
                                           @RequestParam(value = "startTime", required = false) String  startTime,
                                           @RequestParam (value = "endTime", required = false)String  endTime) {


        LocalDate startD= StringUtils.isEmpty(startDate)? DateTimeUtil.MIN_DATE:LocalDate.parse(startDate);
        LocalDate endD= StringUtils.isEmpty(endDate)? DateTimeUtil.MAX_DATE:LocalDate.parse(endDate);
        LocalTime startT= StringUtils.isEmpty(startTime)? LocalTime.MIN:LocalTime.parse(startTime);
        LocalTime endT=StringUtils.isEmpty(endTime)? LocalTime.MAX:LocalTime.parse(endTime);

        return super.getBetween(startD, startT, endD, endT);
    }
}