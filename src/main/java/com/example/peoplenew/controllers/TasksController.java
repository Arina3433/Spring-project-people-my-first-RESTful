package com.example.peoplenew.controllers;

import com.example.peoplenew.dtos.TasksDto;
import com.example.peoplenew.services.TasksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // помечает класс как Spring MVC Controller, но и
// автоматически преобразует возвращаемые контроллером данные в формат JSON или XML
@RequestMapping("/tasks")
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class TasksController {

    private final TasksServiceImpl tasksServiceImpl;

    @PostMapping("/add_task_to/{people_id}")
    ResponseEntity<TasksDto> addTask(@PathVariable("people_id") Long peopleId,
                                     @RequestBody TasksDto tasksDto) {
        // @PathVariable - для связывания значений из URL
        // когда параметры передаются в URL (/get/12)
        // @RequestBody сопоставляет тело HttpRequest с объектом передачи или домена,
        // обеспечивая автоматическую десериализацию входящего тела HttpRequest в объект Java

        return ResponseEntity.ok(tasksServiceImpl.create(peopleId, tasksDto));
        // Чтобы в ответе показывалась сущность, созданная в этом запросе
    }

    @GetMapping("/get_tasks_of/{people_id}")
    ResponseEntity<List<TasksDto>> getAllTasks(@PathVariable("people_id") Long peopleId) {
        return ResponseEntity.ok(tasksServiceImpl.get(peopleId));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<TasksDto> updateTask(@RequestBody TasksDto tasksDto,
                                        @PathVariable("id") Long id) {
        return ResponseEntity.ok(tasksServiceImpl.update(id, tasksDto));
    }

    @DeleteMapping("/delete_task/{id}")
    String deleteTask(@PathVariable("id") Long id) {

        TasksDto tasksDto = tasksServiceImpl.getOne(id);
        // Можно перенести в сервисы, а можно вообще убрать
        tasksServiceImpl.delete(id);

        return "Deleted:\n" + tasksDto;
    }

    @DeleteMapping("/delete_all/{people_id}")
    String deleteAllTasks(@PathVariable("people_id") Long peopleId) {

        tasksServiceImpl.deleteAll(peopleId);
        return "All is deleted";
    }
}
