package com.example.peoplenew.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PeopleDtoWithoutTasks {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
}
