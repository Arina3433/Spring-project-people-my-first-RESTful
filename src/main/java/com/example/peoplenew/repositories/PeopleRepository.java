package com.example.peoplenew.repositories;

import com.example.peoplenew.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Long> {
    Optional<People> findPeopleByName(String name);
    // Optional - обертка для обработки null email-ов (реализованно в сервисах)

    void deleteByName(String name);
}
