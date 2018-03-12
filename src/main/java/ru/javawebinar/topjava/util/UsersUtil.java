package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;
/**
 * Created by 123 on 11.03.2018.
 */
public class UsersUtil {


    public static final List<User> USERS = Arrays.asList(
            new User(null,"user","user@mail.ru","userpassword",Role.ROLE_USER),
            new User(null,"admin","admin@mail.ru","adminpassword",Role.ROLE_ADMIN,Role.values())
    );
}
