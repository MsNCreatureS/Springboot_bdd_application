package com.erwan.Springboot_bdd_application.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erwan.Springboot_bdd_application.Models.Person;
import com.erwan.Springboot_bdd_application.Services.PersonService; // Attention à bien importer ton nouveau Service !

@RestController
@RequestMapping("api/persons")
public class PersonController {
	
	// 1. On déclare le Service (le Chef Cuisinier) au lieu du Repository
	final PersonService personService;

	// 2. Injection de dépendance via le constructeur
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	// Récupérer toutes les personnes
	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons() {
        // Le contrôleur demande simplement au service de faire le travail
		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
	}
	
	// Créer une personne
	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person personCreated = personService.createPerson(person);
		return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
	}
	
	// Trouver par ID
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		Optional<Person> person = personService.getPersonById(id);
		
		if(person.isPresent()) {
			return new ResponseEntity<>(person.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Mettre à jour une personne
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
		// Toute la logique de modification (les setCity, setPhoneNumber) a été déplacée dans le Service !
        // Le contrôleur est beaucoup plus léger.
		Person updatedPerson = personService.updatePerson(id, personDetails);
		
		if(updatedPerson != null) {
			return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Supprimer une personne
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
		// On vérifie si la personne existe via le service
		Optional<Person> person = personService.getPersonById(id);
		
		if(person.isPresent()) {
			personService.deletePerson(id); // On demande au service de supprimer
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	//Supprimer tout le monde
	@DeleteMapping("/all")
	public ResponseEntity<Void> deleteAllPerson() {
		personService.deleteAllPerson();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}