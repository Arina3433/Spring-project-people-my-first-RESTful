package com.example.peoplenew.controllers;

import com.example.peoplenew.dtos.PeopleDto;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.services.DefaultPeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // помечает класс как Spring MVC Controller, но и
// автоматически преобразует возвращаемые контроллером данные в формат JSON или XML
@RequestMapping("/people")
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleController {

    private final DefaultPeopleService defaultPeopleService;

    @PostMapping("/create")
    ResponseEntity<People> create(@RequestBody PeopleDto peopleDto) {
        // @RequestBody сопоставляет тело HttpRequest с объектом передачи или домена,
        // обеспечивая автоматическую десериализацию входящего тела HttpRequest в объект Java

        People person = defaultPeopleService.create(peopleDto);
        return ResponseEntity.ok(person);
        // Чтобы в ответе показывалась сущность, созданная в этом запросе
    }

    @GetMapping("/get_one")
    ResponseEntity<People> getFromParameter(@RequestParam("name") String name) {
        // @RequestParam - для извлечения параметров запроса (только параметров)
        // когда параметры передаются в URL после символа '?'

        People person = defaultPeopleService.get(name);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/get_one/{name}")
    ResponseEntity<People> getFromUrl(@PathVariable("name") String name) {
        // @PathVariable - для связывания значений из URL
        // когда параметры передаются в URL (/get/Ann)

        People person = defaultPeopleService.get(name);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/get_all")
    ResponseEntity<List<People>> getAll() {

        List<People> peopleList = defaultPeopleService.getAll();
        return ResponseEntity.ok(peopleList);
    }

    @PutMapping("/update")
    ResponseEntity<People> updateFromBody(@RequestBody PeopleDto peopleDto) {
        return ResponseEntity.ok(defaultPeopleService.update(peopleDto));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<People> updateFromUrl(@RequestBody PeopleDto peopleDto,
                                         @PathVariable("id") Long id) {
        peopleDto.setId(id);
        return ResponseEntity.ok(defaultPeopleService.update(peopleDto));
    }

    @DeleteMapping("/delete_one")
    String deleteFromParameter(@RequestParam("name") String name) {

        People person = defaultPeopleService.get(name);
        defaultPeopleService.delete(name);

        return "Deleted:\n" + person.toStringInDeleteMethod();
    }

    @DeleteMapping("/delete_one/{name}")
    String deleteFromUrl(@PathVariable("name") String name) {

        People person = defaultPeopleService.get(name);
        defaultPeopleService.delete(name);

        return "Deleted:\n" + person.toStringInDeleteMethod();
    }

    @DeleteMapping("/delete_all")
    String deleteAll() {

        defaultPeopleService.deleteAll();
        return "All is deleted";
    }

}
