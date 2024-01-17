package com.example.peoplenew.controllers;

import com.example.peoplenew.dtos.PeopleDto;
import com.example.peoplenew.entities.People;
import com.example.peoplenew.services.DefaultPeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // помечает класс как Spring MVC Controller, но и
// автоматически преобразует возвращаемые контроллером данные в формат JSON или XML
@RequestMapping("/people")
@RequiredArgsConstructor // Создает конструкторы для всех полей которые либо final, либо @NotNull
public class PeopleController {

    private final DefaultPeopleService defaultPeopleService;

    @PostMapping("/create")
    ResponseEntity<People> create(@RequestBody PeopleDto peopleDto) {
        // сопоставляет тело HttpRequest с объектом передачи или домена,
        // обеспечивая автоматическую десериализацию входящего тела HttpRequest в объект Java

        People person = defaultPeopleService.create(peopleDto);
        return ResponseEntity.ok(person);
        // Чтобы в ответе показывалась сущность, созданная в этом запросе
    }


//    People get(String email){
//
//    }
//
//    List<People> getAll(){
//
//    }
//
//    void update(PeopleDto peopleDto){
//
//    }
//
//    void delete(String email){
//
//    }
//
//    void deleteAll(){
//
//    }


}
