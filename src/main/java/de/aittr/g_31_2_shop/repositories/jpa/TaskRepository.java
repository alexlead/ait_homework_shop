package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    /*
    Домашнее задание 18
    1. Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
    Время выполнения предыдущей задачи не должно влиять на старт следующей.
    Создавать новую задачу и логировать ничего не нужно.
    */

    @Query(value = "select * from task order by id desc limit :count ;", nativeQuery = true)
    List<Task> findLastTasks(@Param("count") int count);
}