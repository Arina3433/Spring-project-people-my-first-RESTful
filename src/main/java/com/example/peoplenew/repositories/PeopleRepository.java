package com.example.peoplenew.repositories;

import com.example.peoplenew.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Long> {
    // Представляет базовый функционал CRUD репозитория, такие методы как save, findById, findAll, deleteById и т.д.
    Optional<People> findPeopleByName(String name);
    // Optional - обертка для обработки null имен (было реализованно в сервисах, теперь нету)
}
