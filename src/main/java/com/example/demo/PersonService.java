package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository personPerssitence;

    public PersonService(PersonRepository personPerssitence) {
        this.personPerssitence = personPerssitence;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Person create(Person person){
        return personPerssitence.save(person);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Person> findAll(){
        List<Person> people = new ArrayList<Person>();
        Iterator<Person> iterator = personPerssitence.findAll().iterator();
        iterator.forEachRemaining(people::add);
        return people;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Person findById(Long id){
        return personPerssitence.findById(id).orElseThrow(()->new EntityNotFoundException("person not found with id : "+id));
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Person person){
        personPerssitence.delete(person);
    }
}
