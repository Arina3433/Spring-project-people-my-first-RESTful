package com.example.peoplenew.controllers;

import com.example.peoplenew.dtos.PeopleDtoWithTasks;
import com.example.peoplenew.dtos.PeopleDtoWithoutTasks;
import com.example.peoplenew.services.PeopleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // помечает класс как Spring MVC Controller, но и
// автоматически преобразует возвращаемые контроллером данные в формат JSON или XML
@RequestMapping("/people")
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleController {

    private final PeopleServiceImpl peopleService;

    @PostMapping("/create")
    ResponseEntity<PeopleDtoWithoutTasks> create(@RequestBody PeopleDtoWithoutTasks peopleDto) {
        // @RequestBody сопоставляет тело HttpRequest с объектом передачи или домена,
        // обеспечивая автоматическую десериализацию входящего тела HttpRequest в объект Java

        return ResponseEntity.ok(peopleService.create(peopleDto));
        // Чтобы в ответе показывалась сущность, созданная в этом запросе
    }

    @GetMapping("/get_one")
    ResponseEntity<PeopleDtoWithoutTasks> getFromParameter(@RequestParam("id") Long id) {
        // @RequestParam - для извлечения параметров запроса (только параметров)
        // когда параметры передаются в URL после символа '?'

        return ResponseEntity.ok(peopleService.get(id));
    }

    @GetMapping("/get_one/{id}")
    ResponseEntity<PeopleDtoWithoutTasks> getFromUrl(@PathVariable("id") Long id) {
        // @PathVariable - для связывания значений из URL
        // когда параметры передаются в URL (/get/12)

        return ResponseEntity.ok(peopleService.get(id));
    }

    @GetMapping("/get_one_with_tasks/{id}")
    ResponseEntity<PeopleDtoWithTasks> getFromUrlWithTasks(@PathVariable("id") Long id) {

        return ResponseEntity.ok(peopleService.getWithTasks(id));
    }

    @GetMapping("/get_all")
    ResponseEntity<List<PeopleDtoWithoutTasks>> getAll() {
        return ResponseEntity.ok(peopleService.getAll());
    }

    @GetMapping("/get_all_with_tasks")
    ResponseEntity<List<PeopleDtoWithTasks>> getAllWithTasks() {
        return ResponseEntity.ok(peopleService.getAllWithTasks());
    }

    @PutMapping("/update/{id}")
    ResponseEntity<PeopleDtoWithoutTasks> updateFromUrl(@RequestBody PeopleDtoWithoutTasks peopleDto,
                                                        @PathVariable("id") Long id) {

        return ResponseEntity.ok(peopleService.update(peopleDto, id));
    }

    @DeleteMapping("/delete_one/{id}")
    ResponseEntity<String> deleteFromUrl(@PathVariable("id") Long id) {

        return ResponseEntity.ok(peopleService.delete(id));
    }

    @DeleteMapping("/delete_all")
    ResponseEntity<String> deleteAll() {

        return ResponseEntity.ok(peopleService.deleteAll());
    }

}
