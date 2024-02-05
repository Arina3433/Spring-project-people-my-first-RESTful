package com.example.peoplenew.services;

import com.example.peoplenew.dtos.PeopleDto;

import java.util.List;

public interface PeopleService {

    PeopleDto create(PeopleDto peopleDto);

    PeopleDto get(Long id);

    PeopleDto getWithTasks(Long id);

    List<PeopleDto> getAll();

    PeopleDto update(PeopleDto peopleDto, Long id);

    void delete(Long id);

    void deleteAll();

}
