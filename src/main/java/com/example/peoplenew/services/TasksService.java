package com.example.peoplenew.services;

import com.example.peoplenew.dtos.TasksDto;

import java.util.List;

public interface TasksService {

    TasksDto create(Long peopleId, TasksDto tasksDto);

    List<TasksDto> getAllByPeopleId(Long peopleId);

    TasksDto update(Long id, TasksDto tasksDto);

    String delete(Long id);

    String deleteAllTasks(Long peopleId);
}
