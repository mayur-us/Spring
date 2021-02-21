package com.emlebi.restwebservices.units;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emlebi.restwebservices.model.Person;
import com.emlebi.restwebservices.repositories.PersonJpaRepository;

/*
 * JUnit Test Class to directly test the Database operations at the JPA Repository Layer.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonTest {
	
	@Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PersonJpaRepository repository;

    
    /*
     * This Test method tests the Save of Entity 'Person' followed by 'Fetch'/'get' of the same Person object using the Id.
     */
    @Test
    public void testPersistandFetch() {
    	
       	Person person = new Person();
        person.setFirst_name("Zydus");
        person.setLast_name("Cadila");
        person.setAge("25");
        person.setFavourite_colour("Ivory");
        
        testEntityManager.persist(person);
        testEntityManager.flush();

        Person newPerson = repository.getOne(person.getId());
        assertEquals("Zydus", newPerson.getFirst_name());

    }

    //Similar to the above test case more test methods can be added for other DB operations.

}
