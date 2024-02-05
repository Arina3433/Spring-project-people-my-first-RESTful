package com.example.peoplenew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING)
    // Хранит enum в виде строки
    private Categories categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    // @JoinColumn используется для указания поля или столбца, который будет использоваться для внешнего ключа
    // Столбец people_id в таблице tasks связан со столбцом id в таблице people
    @JsonIgnore
    private People people;

}
