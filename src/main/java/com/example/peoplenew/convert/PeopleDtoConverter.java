package com.example.peoplenew.convert;

import com.example.peoplenew.dtos.PeopleDto;
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

    public People convertToPeople(PeopleDto peopleDto) {
        return modelMapper.map(peopleDto, People.class);
    }

    public PeopleDto convertToPeopleDto(People people) {
        return modelMapper.map(people, PeopleDto.class);
    }

    public List<PeopleDto> convertToPeopleDtoList(List<People> peopleList) {

        return peopleList.stream()
                .map(people -> modelMapper.map(people, PeopleDto.class))
                .collect(Collectors.toList());
    }
}
