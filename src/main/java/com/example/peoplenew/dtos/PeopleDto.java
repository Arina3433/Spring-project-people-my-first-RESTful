package com.example.peoplenew.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PeopleDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
}
