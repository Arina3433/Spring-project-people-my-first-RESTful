package com.example.peoplenew.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "people_generator")
    @SequenceGenerator(name = "people_generator", sequenceName = "people_seq", allocationSize = 1)
    private Long id;

    private String email;
    private String name;
    private String surname;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "people", fetch = FetchType.LAZY)
    // cascade описывает, что должно происходить с зависимыми объектами, если мы меняем их родительский (главный) объект
    // CascadeType.ALL означает, что все действия, которые мы выполняем с родительским объектом,
    // нужно повторить и для его зависимых объектов

    // mappedBy указывает на поле владельца связи
    // ("people" - объект People people в классе Tasks, именно так устанавливается связь)
    private List<Tasks> tasks;

    public List<Tasks> addTasks(Tasks task) {
        tasks.add(task);

        return tasks;
    }
}
