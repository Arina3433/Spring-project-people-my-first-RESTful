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
    public TasksDto create(Long peopleId, TasksDto tasksDto) {
        People person = peopleRepository.findById(peopleId).orElseThrow(() ->
                new IllegalArgumentException("No user by " + peopleId + " id"));

        Tasks tasks = tasksDtoConverter.convertToTasks(tasksDto);
        tasks.setPeople(person);

//        int taskNumber = person.getTasks().size() + 1;
//        tasks.setTaskNumber(taskNumber);

        tasksRepository.save(tasks);

        return tasksDto;
    }

    @Override
    public List<TasksDto> get(Long peopleId) {
        List<Tasks> tasksList = tasksRepository.findTasksByPeopleId(peopleId).orElseThrow(() ->
                new IllegalArgumentException("No user by " + peopleId + " id"));

        return tasksDtoConverter.convertToTasksDtoList(tasksList);
    }

    @Override
    public TasksDto getOne(Long id) {

        Tasks tasks = tasksRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No task by " + id + " id"));

        return tasksDtoConverter.convertToTasksDto(tasks);
    }

    @Override
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
    public void delete(Long id) {
        tasksRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(Long peopleId) {
        tasksRepository.deleteByPeopleId(peopleId);
    }
}
