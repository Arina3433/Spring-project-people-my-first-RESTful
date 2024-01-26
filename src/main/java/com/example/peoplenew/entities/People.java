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

    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
    private List<Tasks> tasks;
}
