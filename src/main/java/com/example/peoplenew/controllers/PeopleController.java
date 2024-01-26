package com.example.peoplenew.controllers;

import com.example.peoplenew.dtos.PeopleDto;
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
    ResponseEntity<PeopleDto> create(@RequestBody PeopleDto peopleDto) {
        // @RequestBody сопоставляет тело HttpRequest с объектом передачи или домена,
        // обеспечивая автоматическую десериализацию входящего тела HttpRequest в объект Java

        return ResponseEntity.ok(defaultPeopleService.create(peopleDto));
        // Чтобы в ответе показывалась сущность, созданная в этом запросе
    }

    @GetMapping("/get_one")
    ResponseEntity<PeopleDto> getFromParameter(@RequestParam("id") Long id) {
        // @RequestParam - для извлечения параметров запроса (только параметров)
        // когда параметры передаются в URL после символа '?'

        return ResponseEntity.ok(defaultPeopleService.get(id));
    }

    @GetMapping("/get_one/{id}")
    ResponseEntity<PeopleDto> getFromUrl(@PathVariable("id") Long id) {
        // @PathVariable - для связывания значений из URL
        // когда параметры передаются в URL (/get/12)

        return ResponseEntity.ok(defaultPeopleService.get(id));
    }

    @GetMapping("/get_all")
    ResponseEntity<List<PeopleDto>> getAll() {
        return ResponseEntity.ok(defaultPeopleService.getAll());
    }

    @PutMapping("/update/{id}")
    ResponseEntity<PeopleDto> updateFromUrl(@RequestBody PeopleDto peopleDto,
                                            @PathVariable("id") Long id) {
        return ResponseEntity.ok(defaultPeopleService.update(peopleDto, id));
    }

    @DeleteMapping("/delete_one/{id}")
    String deleteFromUrl(@PathVariable("id") Long id) {

        PeopleDto peopleDto = defaultPeopleService.get(id);
        defaultPeopleService.delete(id);

        return "Deleted:\n" + peopleDto;
    }

    @DeleteMapping("/delete_all")
    String deleteAll() {

        defaultPeopleService.deleteAll();
        return "All is deleted";
    }

}
