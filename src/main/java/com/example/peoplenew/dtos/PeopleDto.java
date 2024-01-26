package com.example.peoplenew.dtos;

import com.example.peoplenew.entities.Tasks;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PeopleDto {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<Tasks> tasks;

}
