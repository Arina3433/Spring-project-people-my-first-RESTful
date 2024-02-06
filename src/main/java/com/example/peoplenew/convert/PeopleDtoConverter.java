package com.example.peoplenew.convert;

import com.example.peoplenew.dtos.PeopleDtoWithTasks;
import com.example.peoplenew.dtos.PeopleDtoWithoutTasks;
import com.example.peoplenew.entities.People;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleDtoConverter {

    private final ModelMapper modelMapper;

    public People convertToPeople(PeopleDtoWithTasks peopleDtoWithTasks) {
        return modelMapper.map(peopleDtoWithTasks, People.class);
    }

    public People convertToPeople(PeopleDtoWithoutTasks peopleDtoWithoutTasks) {
        return modelMapper.map(peopleDtoWithoutTasks, People.class);
    }


    public PeopleDtoWithTasks convertToPeopleDtoWithTasks(People people) {
        return modelMapper.map(people, PeopleDtoWithTasks.class);
    }

    public PeopleDtoWithoutTasks convertToPeopleDtoWithoutTasks(People people) {
        return modelMapper.map(people, PeopleDtoWithoutTasks.class);
    }


    public List<PeopleDtoWithTasks> convertToPeopleDtoWithTasksList(List<People> peopleList) {

        return peopleList.stream()
                .map(people -> modelMapper.map(people, PeopleDtoWithTasks.class))
                .collect(Collectors.toList());
    }

    public List<PeopleDtoWithoutTasks> convertToPeopleDtoWithoutTasksList(List<People> peopleList) {

        return peopleList.stream()
                .map(people -> modelMapper.map(people, PeopleDtoWithoutTasks.class))
                .collect(Collectors.toList());
    }
}
