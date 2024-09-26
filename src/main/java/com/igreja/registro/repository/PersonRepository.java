package com.igreja.registro.repository;

import com.igreja.registro.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long> {

}
