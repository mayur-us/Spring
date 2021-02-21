package com.emlebi.restwebservices.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emlebi.restwebservices.model.Person;
import com.emlebi.restwebservices.repositories.PersonJpaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * Controller class for Person. Front end class for accessing
 * REST endpoints for the entity 'Person'
 * Rest endpoints for following functionalities are available
 * 1)Get all persons
 * 2)Get a particular person
 * 3)Create a person
 * 4)Update a person
 * 5)Delete a person.
 */

@RestController
@RequestMapping("/api/v1/person")
@Api(value = "/api/v1/person")
public class PersonController {

	@Autowired
	private PersonJpaRepository repository;

	@ApiOperation(value = "Get list of Persons")
	@GetMapping
	@RequestMapping(value = "/getAllPersons", method = RequestMethod.GET)
	public List<Person> list() {
		return repository.findAll();
	}

	@ApiOperation(value = "Get person by Id ")
	@GetMapping
	@RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
	public Person get(@PathVariable Long id) {
		return repository.findById(id).get();
	}

	@ApiOperation(value = "Create Person ")
	@PostMapping
	@RequestMapping(value = "/createPerson", method = RequestMethod.POST)
	public Person create(@RequestBody final Person person) {
		return repository.saveAndFlush(person);
	}

	@ApiOperation(value = "Delete Person with Id")
	@DeleteMapping
	@RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@ApiOperation(value = "Update Person with Id")
	@PutMapping
	@RequestMapping(value = "/updatePerson/{id}", method = RequestMethod.PUT)
	public Person update(@PathVariable Long id, @RequestBody Person person) {

		Person existingPerson = repository.findById(id).get();
		BeanUtils.copyProperties(person, existingPerson, "id");
		return repository.saveAndFlush(existingPerson);

	}

}
