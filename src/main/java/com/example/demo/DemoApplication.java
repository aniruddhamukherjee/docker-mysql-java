package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	PersonService service;
	@Autowired
	PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@PostConstruct
	public void checkIfWorks(){
		//repository.deleteAll();
		service.create(new Person("Aniruddha", LocalDate.of(1989,11,23)));
		service.create(new Person("Anil", LocalDate.of(1999,2,2)));
		for(Person person : service.findAll()){
			System.out.println("Id::"+person.getId()+" Name::"+person.getName()+"Birthdate::"+person.getBirthDate().toString());
		}

		Person person = service.findById(100L);
		System.out.println("Found person by id::100");
		System.out.println(person.toString());


	}


}
