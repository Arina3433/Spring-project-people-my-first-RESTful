package com.example.peoplenew.convert;

import com.example.peoplenew.dtos.TasksDto;
import com.example.peoplenew.entities.Tasks;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class TasksDtoConverter {
    private final ModelMapper modelMapper;

    public Tasks convertToTasks(TasksDto tasksDto) {
        return modelMapper.map(tasksDto, Tasks.class);
    }

    public TasksDto convertToTasksDto(Tasks tasks) {
        return modelMapper.map(tasks, TasksDto.class);
    }

    public List<Tasks> convertToTasksList(List<TasksDto> tasksDtoList) {

        return tasksDtoList.stream()
                .map(tasksDto -> modelMapper.map(tasksDto, Tasks.class))
                .collect(Collectors.toList());
    }

    public List<TasksDto> convertToTasksDtoList(List<Tasks> tasksList) {

        return tasksList.stream()
                .map(tasks -> modelMapper.map(tasks, TasksDto.class))
                .collect(Collectors.toList());
    }
}
