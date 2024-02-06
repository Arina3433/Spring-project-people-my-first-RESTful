package com.example.peoplenew.dtos;

import com.example.peoplenew.entities.Categories;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TasksDto {

    private Long id;
    private String name;
    private String deadline;

    @Enumerated(EnumType.STRING)
    // Хранит enum в виде строки
    private Categories categories;

    private Long peopleId;

}
