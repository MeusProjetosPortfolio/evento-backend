package com.igreja.registro.service;

import com.igreja.registro.model.Person;
import com.igreja.registro.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> listarTodos() {
        return personRepository.findAll();
    }

    public Optional<Person> buscarId(Long id) {
        return personRepository.findById(id);
    }

    public Person salvar(Person person) {
        return personRepository.save(person);
    }

    public Person atualizar(Person person){
        return  personRepository.save(person);
    }

    public void deletar(Long id) {
        personRepository.deleteById(id);
    }
}
