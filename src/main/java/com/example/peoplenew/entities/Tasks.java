package com.example.peoplenew.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_generator")
    @SequenceGenerator(name = "tasks_generator", sequenceName = "tasks_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String deadline;

    @ElementCollection(targetClass = Categories.class, fetch = FetchType.EAGER)
    // Формирует таблицу для хранения перечисления. fetch - как будет подгружаться Categories
    // из отдельной таблицы. EAGER - сразу все при запросе пользователей, LAZY - для каждого отдельно по мере необходимости.
    // У нас категорий мало, поэтому EAGER
    @CollectionTable(name = "task_categories", joinColumns = @JoinColumn(name = "tasks_id"))
    // Создает таблицу для категорий (task_categories),
    // которая связана через столбец tasks_id со столбцом id в текущей таблице (tasks)
    @Enumerated(EnumType.STRING)
    // Хранит enum в виде строки
    private Set<Categories> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "people_id")
    // Столбец people_id в таблице tasks связан со столбцом id в таблице people
    private People people;

}
