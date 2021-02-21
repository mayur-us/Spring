package com.emlebi.restwebservices.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.emlebi.restwebservices.RestfulWebServicesApplication;
import com.emlebi.restwebservices.model.Person;
import com.emlebi.restwebservices.repositories.PersonJpaRepository;

/*
 * Integration Test Class 
 */
@SpringBootTest(classes = RestfulWebServicesApplication.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
public class PersonIntegrationTest {
	

    @Autowired
    private PersonJpaRepository repository;


    @Test
    public void testFind() throws Exception {
        Person person = repository.findById(1L).get();
        assertNotNull(person);
    }

//    @Test
//    public void testSaveAndGetAndDelete() throws Exception {
//    	
//    	//Set the Person object to be persisted.
//    	
//        Person person = new Person();
//        person.setFirst_name("Zydus");
//        person.setLast_name("Cadila");
//        person.setAge("25");
//        person.setFavourite_colour("Ivory");
//        
//        //Save the Person
//        person = repository.saveAndFlush(person);
//
//        //Fetch the same person using its PK id.
//        Person newPerson = repository.getOne(person.getId());
//        assertEquals("Zydus", newPerson.getFirst_name());
//
//        //Delete the Persisted Person Object
//        repository.deleteById(newPerson.getId());
//    }
    
   
}
