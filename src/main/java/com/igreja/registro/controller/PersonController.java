package com.igreja.registro.controller;

import com.igreja.registro.dto.PersonDto;
import com.igreja.registro.dto.PersonMapper;
import com.igreja.registro.exception.EntityNotFoundException;
import com.igreja.registro.model.Person;
import com.igreja.registro.service.PdfService;
import com.igreja.registro.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/relatorio-pdf")
    public ResponseEntity<InputStreamResource> gerarRelatorioPdf(){
        ByteArrayInputStream bis = pdfService.gerarRelatorioPdf();

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorio-participantes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping
    public List<PersonDto> listarTodas() {
        List<Person> pessoas = personService.listarTodos();
        return pessoas.stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDto buscarPorId(@PathVariable Long id) {
        /*
        Optional<Person> pessoaExistente = personService.buscarId(id);

        if (pessoaExistente.isPresent()) {
            return PersonMapper.toDto(pessoaExistente.get());
        } else {
            throw new RuntimeException("Pessoa não encontrada com o id : " + id);
        }

         */
        return personService.buscarId(id)
                .map(PersonMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o id " + id));
    }

    @PostMapping
    public PersonDto criarPessoa(@RequestBody PersonDto personDto) {
        Person person = PersonMapper.toEntity(personDto);   
        Person pessoaSalva = personService.salvar(person);
        return PersonMapper.toDto(pessoaSalva);
    }

    @PutMapping("/{id}")
    public PersonDto atualizarDados(@PathVariable Long id, @RequestBody PersonDto personDto) {
        /*
        Optional<Person> pessoaExistente = personService.buscarId(id);

        if (pessoaExistente.isPresent()) {
            Person person = pessoaExistente.get();
            person.setName(personDto.getName());
            person.setChurch(personDto.getChurch());
            person.setCellphone(personDto.getCellphone());

            Person pessoaAtualizada = personService.atualizar(person);

            return PersonMapper.toDto(pessoaAtualizada);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o id " + id);
        }

         */
        if (!personService.buscarId(id).isPresent()) {
            throw new EntityNotFoundException("Pessoa não encontrada com o id " + id);
        }

        Person person = PersonMapper.toEntity(personDto);
        person.setId(id);
        Person personAtualizada = personService.atualizar(person);
        return PersonMapper.toDto(personAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        personService.deletar(id);

    }
}
