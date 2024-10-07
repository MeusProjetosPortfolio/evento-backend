package com.igreja.registro.service;

import com.igreja.registro.exception.EntityNotFoundException;
import com.igreja.registro.exception.InvalidDataException;
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
        if (person.getName().length() > 150){
            throw new InvalidDataException("O nome não pode exceder 150 caracteres");
        }
        return personRepository.save(person);
    }

    public Person atualizar(Person person){
        if (person.getName().length() > 150){
            throw new InvalidDataException("O nome não pode exceder 150 caracteres");
        }
        return  personRepository.save(person);
    }

    public void deletar(Long id) {
        if (!personRepository.existsById(id)){
            throw new EntityNotFoundException("O id " + id + " não pode ser deletado, pois não existe");
        }
        personRepository.deleteById(id);
    }
}
