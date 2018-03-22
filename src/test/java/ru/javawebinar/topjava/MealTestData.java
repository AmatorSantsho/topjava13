package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by 123 on 21.03.2018.
 */
public class MealTestData {
    public static final int USER_ID = 100000;
    public static final int MEAL_ID1 = 100002;
    public static final int MEAL_ID2 = 100003;
    public static final int MEAL_ID3 = 100004;
    public static final int MEAL_ID_OTHER = 100005;
    public static final Meal MEAL1 = new Meal(MEAL_ID1, LocalDateTime.of(2015,05,30,19,00,00),"Ужин",200);
    public static final Meal MEAL2 = new Meal(MEAL_ID2, LocalDateTime.of(2015,05,30,14,00,00),"Обед",400);
    public static final Meal MEAL3 = new Meal(MEAL_ID3, LocalDateTime.of(2015,05,30,9,00,00),"Завтрак",100);
    public static final Meal MEAL_OTHER = new Meal(MEAL_ID_OTHER, LocalDateTime.of(2015,05,30,9,00,00),"Завтрак",250);
    public static final LocalDateTime STARTTIME=LocalDateTime.of(2015,05,30,13,00,00);
    public static final LocalDateTime ENDTIME= LocalDateTime.of(2015, 05, 30, 18, 00, 00);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }
    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
