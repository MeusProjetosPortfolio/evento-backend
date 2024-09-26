package com.igreja.registro.controller;

import com.igreja.registro.dto.PersonDto;
import com.igreja.registro.model.Person;
import com.igreja.registro.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> listarTodas() {
        return personService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Person> buscarPorId(@PathVariable Long id) {
        return personService.buscarId(id);
    }

    @PostMapping
    public Person criarPessoa(@RequestBody PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setChurch(personDto.getChurch());

        return personService.salvar(person);
    }

    @PutMapping("/{id}")
    public Person atualizarDados(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Optional<Person> pessoaExistente = personService.buscarId(id);

        if (pessoaExistente.isPresent()) {
            Person person = pessoaExistente.get();
            person.setName(personDto.getName());
            person.setChurch(personDto.getChurch());

            return personService.atualizar(person);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        personService.deletar(id);

    }
}
