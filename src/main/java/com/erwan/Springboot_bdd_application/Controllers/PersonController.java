package com.erwan.Springboot_bdd_application.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwan.Springboot_bdd_application.Exceptions.ServiceException;
import com.erwan.Springboot_bdd_application.Models.Person;
import com.erwan.Springboot_bdd_application.Services.PersonService; // Attention à bien importer ton nouveau Service !



@CrossOrigin(origins = "http://localhost:4200") // INDISPENSABLE !
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
	public ResponseEntity<Object> createPerson(@RequestBody Person person) {
		
		try {
			// 1. On ESSAIE de créer la personne
			Person personCreated = personService.createPerson(person);
			
			// Si succès : On utilise la syntaxe de ton cours !
			return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
			
		} catch (ServiceException e) {
			// 2. L'alarme a sonné ! On l'ATTRAPE.
			
			// Si erreur : On renvoie le message d'erreur avec le statut 409 CONFLICT
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
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
	public ResponseEntity<Object> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
		
		try {
			
			Person updatedPerson = personService.updatePerson(id, personDetails);
			
			return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
			
		} catch (ServiceException e) {
			
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
		
	}
	
	
	
	// Supprimer une personne
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable Long id) {
		
		try {
			
			personService.deletePerson(id);
			
			return ResponseEntity.ok().build();
			
		} catch (ServiceException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	
	}
	
	
	//Supprimer tout le monde
	@DeleteMapping("/all")
	public ResponseEntity<Void> deleteAllPerson() {
		personService.deleteAllPerson();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	// ajouter un skill a une personne 
	@PutMapping("/{personId}/skills/{skillId}")
	public ResponseEntity<Object> addSkillToPerson(@PathVariable Long personId, @PathVariable Long skillId) {
		
	    try {
	    	
	        personService.addSkillToPerson(personId, skillId);
	        
	        return new ResponseEntity<>("Compétence ajoutée avec succès !", HttpStatus.OK);
	        
	    } catch (ServiceException e) {
	    	
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	
	
	
	
	
}