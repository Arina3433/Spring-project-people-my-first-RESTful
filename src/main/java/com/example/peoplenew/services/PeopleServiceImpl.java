package com.example.peoplenew.services;

import com.example.peoplenew.convert.PeopleDtoConverter;
import com.example.peoplenew.dtos.PeopleDtoWithTasks;
import com.example.peoplenew.dtos.PeopleDtoWithoutTasks;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.repositories.PeopleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PeopleDtoConverter peopleDtoConverter;

    @Override
    @Transactional
    public PeopleDtoWithoutTasks create(PeopleDtoWithoutTasks peopleDto) {
        People people = peopleRepository.save(peopleDtoConverter.convertToPeople(peopleDto));
        peopleDto.setId(people.getId());

        return peopleDto;
    }

    @Override
    public PeopleDtoWithoutTasks get(Long id) {

        People person = peopleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No user by " + id + " id"));

        return peopleDtoConverter.convertToPeopleDtoWithoutTasks(person);
    }

    @Override
    public PeopleDtoWithTasks getWithTasks(Long id) {

        PeopleDtoWithTasks peopleDto = peopleDtoConverter.convertToPeopleDtoWithTasks
                (peopleRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("No user by " + id + " id")));

//        peopleDtoWithTasks.setTasks(tasksRepository.findTasksByPeopleId(id).orElseThrow(() ->
//                new IllegalArgumentException("No user by " + id + " id")));

        return peopleDto;
    }

    @Override
    public List<PeopleDtoWithoutTasks> getAll() {
        return peopleDtoConverter.convertToPeopleDtoWithoutTasksList(peopleRepository.findAll());
    }

    @Override
    public List<PeopleDtoWithTasks> getAllWithTasks() {
        return peopleDtoConverter.convertToPeopleDtoWithTasksList(peopleRepository.findAll());
    }

    @Override
    @Transactional
    public PeopleDtoWithoutTasks update(PeopleDtoWithoutTasks peopleDto, Long id) {
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

        return peopleDtoConverter.convertToPeopleDtoWithoutTasks(person);
    }


    @Override
    @Transactional
    // @Transactional - указывает, что метод должен быть выполнен в рамках одной транзакции
    // Транзакция — группа последовательных операций с базой данных, которая представляет
    // собой логическую единицу работы с данными
    public String delete(Long id) {
        peopleRepository.deleteById(id);

        return "Person with id " + id + " has been deleted";
    }

    @Override
    @Transactional
    public String deleteAll() {
        peopleRepository.deleteAll();
        return "All is deleted";
    }
}
