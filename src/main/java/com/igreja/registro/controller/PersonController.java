package com.igreja.registro.controller;

import com.igreja.registro.dto.PersonDto;
import com.igreja.registro.dto.PersonMapper;
import com.igreja.registro.model.Person;
import com.igreja.registro.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDto> listarTodas() {
        List<Person> pessoas = personService.listarTodos();
        return pessoas.stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDto buscarPorId(@PathVariable Long id) {
        Optional<Person> pessoaExistente = personService.buscarId(id);

        if (pessoaExistente.isPresent()) {
            return PersonMapper.toDto(pessoaExistente.get());
        } else {
            throw new RuntimeException("Pessoa não encontrada com o id : " + id);
        }
    }

    @PostMapping
    public PersonDto criarPessoa(@RequestBody PersonDto personDto) {
        Person person = PersonMapper.toEntity(personDto);
        Person pessoaSalva = personService.salvar(person);
        return PersonMapper.toDto(pessoaSalva);
    }

    @PutMapping("/{id}")
    public PersonDto atualizarDados(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Optional<Person> pessoaExistente = personService.buscarId(id);

        if (pessoaExistente.isPresent()) {
            Person person = pessoaExistente.get();
            person.setName(personDto.getName());
            person.setChurch(personDto.getChurch());

            Person pessoaAtualizada = personService.atualizar(person);

            return PersonMapper.toDto(pessoaAtualizada);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        personService.deletar(id);

    }
}
