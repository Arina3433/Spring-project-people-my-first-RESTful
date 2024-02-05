package com.example.peoplenew.services;

import com.example.peoplenew.convert.PeopleDtoConverter;
import com.example.peoplenew.dtos.PeopleDto;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.repositories.PeopleRepository;
import com.example.peoplenew.repositories.TasksRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;

    private final TasksRepository tasksRepository;
    private final PeopleDtoConverter peopleDtoConverter;

    @Override
    public PeopleDto create(PeopleDto peopleDto) {
        peopleRepository.save(peopleDtoConverter.convertToPeople(peopleDto));

        return peopleDto;
    }

    @Override
    public PeopleDto get(Long id) {

        People person = peopleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No user by " + id + " id"));

        return peopleDtoConverter.convertToPeopleDto(person);
    }

    @Override
    public PeopleDto getWithTasks(Long id) {

        PeopleDto peopleDto = peopleDtoConverter.convertToPeopleDto(peopleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No user by " + id + " id")));

        peopleDto.setTasks(tasksRepository.findTasksByPeopleId(id).orElseThrow(() ->
                new IllegalArgumentException("No user by " + id + " id")));

        return peopleDto;
    }

    @Override
    public List<PeopleDto> getAll() {
        return peopleDtoConverter.convertToPeopleDtoList(peopleRepository.findAll());
    }

    @Override
    public PeopleDto update(PeopleDto peopleDto, Long id) {
        People person = peopleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No user by " + id + " id"));

        if (StringUtils.hasText(peopleDto.getEmail())) {
            person.setEmail(peopleDto.getEmail());
        }
        if (StringUtils.hasText(peopleDto.getName())) {
            person.setName(peopleDto.getName());
        }
        if (StringUtils.hasText(peopleDto.getSurname())) {
            person.setSurname(peopleDto.getSurname());
        }
        if (StringUtils.hasText(peopleDto.getPhoneNumber())) {
            person.setPhoneNumber(peopleDto.getPhoneNumber());
        }

        peopleRepository.save(person);

        return peopleDtoConverter.convertToPeopleDto(person);
    }


    @Override
    @Transactional
    // @Transactional - указывает, что метод должен быть выполнен в рамках одной транзакции
    // Транзакция — группа последовательных операций с базой данных, которая представляет
    // собой логическую единицу работы с данными
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        peopleRepository.deleteAll();
    }
}
