Java Enterprise Online Project 
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

![topjava_structure](https://user-images.githubusercontent.com/13649199/27433714-8294e6fe-575e-11e7-9c41-7f6e16c5ebe5.jpg)

Curl Command for Rest Testing

1.GetAllMeals:  curl "http://localhost:8080/topjava/rest/meals/"
2.GetMealById:  curl "http://localhost:8080/topjava/rest/meals/100004"
3.DeleteMeal:   curl -X DELETE "http://localhost:8080/topjava/rest/meals/100002"
4.UpdateMeal:   curl -X PUT -H "Content-Type: application/json" -d '{"dateTime":"2018-04-24T10:00:00","description":"NewEat","calories":600}' "http://localhost:8080/topjava/rest/meals/100003"
5.CreateMeal:   curl -X POST -H "Content-Type: application/json" -d '{"dateTime":"2018-04-25T10:00:00","description":"New","calories":500}' "http://localhost:8080/topjava/rest/meals/"
6.FilterMeal:   curl "http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-30&endDate=2015-05-30&startTime=18:00&endTime=21:00"

7.GetAllUsers:  curl "http://localhost:8080/topjava/rest/admin/users"
