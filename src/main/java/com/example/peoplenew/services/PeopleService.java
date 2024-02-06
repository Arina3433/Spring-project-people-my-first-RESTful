package com.example.peoplenew.services;

import com.example.peoplenew.dtos.PeopleDtoWithTasks;
import com.example.peoplenew.dtos.PeopleDtoWithoutTasks;

import java.util.List;

public interface PeopleService {

    PeopleDtoWithoutTasks create(PeopleDtoWithoutTasks peopleDto);

    PeopleDtoWithoutTasks get(Long id);

    PeopleDtoWithTasks getWithTasks(Long id);

    List<PeopleDtoWithoutTasks> getAll();

    List<PeopleDtoWithTasks> getAllWithTasks();

    PeopleDtoWithoutTasks update(PeopleDtoWithoutTasks peopleDto, Long id);

    String delete(Long id);

    String deleteAll();
}
