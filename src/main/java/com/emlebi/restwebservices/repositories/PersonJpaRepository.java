package com.emlebi.restwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlebi.restwebservices.model.Person;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {

}
