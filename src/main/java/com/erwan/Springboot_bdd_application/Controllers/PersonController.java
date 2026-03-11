package com.erwan.Springboot_bdd_application.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwan.Springboot_bdd_application.Models.Person;
import com.erwan.Springboot_bdd_application.Repository.PersonRepository;

@RestController
@RequestMapping("api/persons")
public class PersonController {
	
		
	final PersonRepository personRepository;

	public PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	
	// fonction poir avoir toutes les personne
	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons() {
		
		return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
		
	}
	
	// fonction pour crée une personne
	
	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		
		Person personCreated = personRepository.save(person);
		
		return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
	}
	
	//Fonction pour trouver by ID 
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		
		Optional<Person> person = personRepository.findById(id);
		
		if(person.isPresent()) {
			
			return new ResponseEntity<>(person.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	

}
