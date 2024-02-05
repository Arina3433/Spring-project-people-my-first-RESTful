package com.example.peoplenew.repositories;

import com.example.peoplenew.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    // Представляет базовый функционал CRUD репозитория, такие методы как save, findById, findAll, deleteById и т.д.

    Optional<List<Tasks>> findTasksByPeopleId(Long peopleId);

    void deleteByPeopleId(Long peopleId);
}
