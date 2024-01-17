package com.example.peoplenew.services;

import com.example.peoplenew.dtos.PeopleDto;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class DefaultPeopleService implements PeopleService {
    private final PeopleRepository peopleRepository;

    @Override
    public People create(PeopleDto peopleDto) {
        People person = new People();

        person.setEmail(peopleDto.getEmail());
        person.setName(peopleDto.getName());
        person.setSurname(peopleDto.getSurname());
        person.setPhoneNumber(peopleDto.getPhoneNumber());

        return peopleRepository.save(person);
    }

    @Override
    public People get(String name) {
        return peopleRepository.findPeopleByName(name).orElseThrow(() ->
                new IllegalArgumentException("No user by " + name + " name"));
    }

    @Override
    public List<People> getAll() {
        return peopleRepository.findAll();
    }

    @Override
    public void update(PeopleDto peopleDto) {
        People person = peopleRepository.findPeopleByName(peopleDto.getName()).orElseThrow(() ->
                new IllegalArgumentException("No user by " + peopleDto.getName() + " name"));
        ;

        person.setEmail(peopleDto.getEmail());
        person.setName(peopleDto.getName());
        person.setSurname(peopleDto.getSurname());
        person.setPhoneNumber(peopleDto.getPhoneNumber());

        peopleRepository.save(person);
    }

    @Override
    public void delete(String name) {
        peopleRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        peopleRepository.deleteAll();
    }
}