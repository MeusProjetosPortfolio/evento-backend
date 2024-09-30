package com.igreja.registro.dto;

import com.igreja.registro.model.Person;

public class PersonMapper {
    public static PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setChurch(person.getChurch());
        dto.setCellphone(person.getCellphone());
        return dto;
    }

    public static Person toEntity(PersonDto dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setChurch(dto.getChurch());
        person.setCellphone(dto.getCellphone());
        return person;
    }
}
