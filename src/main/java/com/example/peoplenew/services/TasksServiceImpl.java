package com.example.peoplenew.services;

import com.example.peoplenew.convert.TasksDtoConverter;
import com.example.peoplenew.dtos.TasksDto;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.entities.Tasks;
import com.example.peoplenew.repositories.PeopleRepository;
import com.example.peoplenew.repositories.TasksRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class TasksServiceImpl implements TasksService {

    private final PeopleRepository peopleRepository;
    private final TasksRepository tasksRepository;
    private final TasksDtoConverter tasksDtoConverter;

    @Override
    @Transactional
    public TasksDto create(Long peopleId, TasksDto tasksDto) {
        People person = peopleRepository.findById(peopleId).orElseThrow(() ->
                new IllegalArgumentException("No user by " + peopleId + " id"));

        Tasks tasks = tasksDtoConverter.convertToTasks(tasksDto);
        tasks.setPeople(person);

        tasks = tasksRepository.save(tasks);

        tasksDto.setId(tasks.getId());
        tasksDto.setPeopleId(peopleId);

        return tasksDto;
    }

    @Override
    public List<TasksDto> getAllByPeopleId(Long peopleId) {
        List<Tasks> tasksList = tasksRepository.findTasksByPeopleId(peopleId).orElseThrow(() ->
                new IllegalArgumentException("No user by " + peopleId + " id"));

        return tasksDtoConverter.convertToTasksDtoList(tasksList);
    }

    @Override
    @Transactional
    public TasksDto update(Long id, TasksDto tasksDto) {

        Tasks tasks = tasksRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No task by " + id + " id"));

        if (StringUtils.hasText(tasksDto.getName())) {
            tasks.setName(tasksDto.getName());
        }
        if (StringUtils.hasText(tasksDto.getDeadline())) {
            tasks.setDeadline(tasksDto.getDeadline());
        }
        if (tasksDto.getCategories() != null) {
            tasks.setCategories(tasksDto.getCategories());
        }

        tasksRepository.save(tasks);

        return tasksDtoConverter.convertToTasksDto(tasks);
    }

    @Override
    @Transactional
    // @Transactional - указывает, что метод должен быть выполнен в рамках одной транзакции
    // Транзакция — группа последовательных операций с базой данных, которая представляет
    // собой логическую единицу работы с данными
    public String delete(Long id) {
        tasksRepository.deleteById(id);

        return "Task with id " + id + " has been deleted";
    }

    @Override
    @Transactional
    public String deleteAllTasks(Long peopleId) {
        tasksRepository.deleteByPeopleId(peopleId);

        return "All tasks of a person with id " + peopleId + " have been deleted.";
    }
}
