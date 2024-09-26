package com.igreja.registro.dto;

import com.igreja.registro.model.Person;

public class PersonMapper {
    public static PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setName(person.getName());
        dto.setChurch(person.getChurch());
        return dto;
    }

    public static Person toEntity(PersonDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setChurch(dto.getChurch());
        return person;
    }
}
