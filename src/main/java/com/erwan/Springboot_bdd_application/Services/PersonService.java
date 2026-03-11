package com.erwan.Springboot_bdd_application.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erwan.Springboot_bdd_application.Models.Person;
import com.erwan.Springboot_bdd_application.Repository.PersonRepository;

@Service
@Transactional // Recommandé par ton cours pour gérer automatiquement les sauvegardes de manière sécurisée
public class PersonService {

	// 2. Déclaration du repository (le "frigo")
	private final PersonRepository personRepository;

	// 3. Injection du repository via le constructeur
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	// --- DÉBUT DES RÈGLES MÉTIER ET ACCÈS AUX DONNÉES ---

	// Récupérer toutes les personnes
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	// Créer une personne
	public Person createPerson(Person person) {
		// Ici, tu pourras ajouter tes "if" de vérification plus tard !
		return personRepository.save(person);
	}

	// Trouver une personne par son ID
	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	// Supprimer une personne
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}

    // Mettre à jour une personne
    public Person updatePerson(Long id, Person personDetails) {
        Optional<Person> person = personRepository.findById(id);
		
		if(person.isPresent()) {
			Person existingPerson = person.get();
			existingPerson.setCity(personDetails.getCity());
			existingPerson.setPhonenumber(personDetails.getPhonenumber());
			
			return personRepository.save(existingPerson);
		}
		return null; // On gérera les erreurs plus proprement plus tard !
    }
    
    
    // Supprimer toutes les personnes
    public void deleteAllPerson() {
    	
    	 personRepository.deleteAll();
    	 
    }
}