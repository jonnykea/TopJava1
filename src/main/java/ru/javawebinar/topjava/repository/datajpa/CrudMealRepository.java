package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
    int delete(@Param("id") int id,
               @Param("userId") int userId);


    @Query(name = Meal.GET_BETWEEN)
    List<Meal> findAllByBetweenSorted(@Param("userId") int userId,
                                      @Param("startDateTime") LocalDateTime startDateTime,
                                      @Param("endDateTime") LocalDateTime endDateTime);

    @Query(name = Meal.ALL_SORTED)
    List<Meal> findAllSorted(@Param("userId") int userId);

    @Query(name = Meal.GET_WITH_USER)
    Meal getWithUser(@Param("id") int id,
                     @Param("userId") int userId);
}

