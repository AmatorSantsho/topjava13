package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(em.getReference(User.class, userId));
            em.persist(meal);
        } else {
            if (em.createQuery("UPDATE Meal m set m.dateTime=:date_time, m.calories=:calories," +
                    "m.description=:description where m.id=:id and m.user.id=:userId")
                    .setParameter("date_time", meal.getDateTime()).setParameter("calories", meal.getCalories())
                    .setParameter("description", meal.getDescription())
                    .setParameter("id", meal.getId())
                    .setParameter("userId", userId)
                    .executeUpdate() == 0)
                return null;
        }
        return meal;
    }
    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id",id).
                setParameter("userId",userId).executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = null;
        try {
            meal = (Meal) em.createQuery("SELECT m from Meal m LEFT JOIN FETCH m.user WHERE m.id=:id and m.user.id=:user_id")
                    .setParameter("id", id)
                    .setParameter("user_id", userId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return  em.createQuery("SELECT m FROM Meal m LEFT JOIN FETCH m.user  WHERE m.user.id =:user_id AND m.dateTime BETWEEN ?1 AND ?2 ORDER BY m.dateTime DESC", Meal.class)
                .setParameter("user_id",userId)
                .setParameter(1,startDate)
                .setParameter(2,endDate)
                .getResultList();
    }
}