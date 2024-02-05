package com.example.peoplenew.services;

import com.example.peoplenew.dtos.TasksDto;

import java.util.List;

public interface TasksService {

    TasksDto create(Long peopleId, TasksDto tasksDto);

    List<TasksDto> get(Long peopleId);

    TasksDto update(Long id, TasksDto tasksDto);

    void delete(Long id);

    void deleteAll(Long peopleId);

    TasksDto getOne(Long id);
}
