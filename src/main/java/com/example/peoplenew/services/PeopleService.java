package com.example.peoplenew.services;

import com.example.peoplenew.dtos.PeopleDto;
import com.example.peoplenew.entities.People;

import java.util.List;

public interface PeopleService {

    People create(PeopleDto peopleDto);

    People get(String email);

    List<People> getAll();

    void update(PeopleDto peopleDto);

    void delete(String email);

    void deleteAll();

}
