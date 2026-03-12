package com.erwan.Springboot_bdd_application.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erwan.Springboot_bdd_application.Exceptions.ServiceException;
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
	public Person createPerson(Person person) throws ServiceException {
		
		Optional<Person> existingPerson = personRepository.findByName(person.getName());
		
		if(existingPerson.isPresent()) {
			// On ne fait plus "return null;". On LÈVE L'EXCEPTION !
			throw new ServiceException("Impossible de créer la personne : le nom '" + person.getName() + "' existe déjà !");
		}
		
		return personRepository.save(person);
	}

	// Trouver une personne par son ID
	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	// Supprimer une personne
	public void deletePerson(Long id) throws ServiceException  {
	    
		
	    if(id <= 0) {
	        throw new ServiceException("Impossible de supprimer la personne : l'ID doit etre > 0");
	    }
	    
	    // 2. On vérifie si elle n'existe PAS (le "!" veut dire "NOT")
	    if(!personRepository.existsById(id)) {
	        throw new ServiceException("Impossible de supprimer la personne : Elle n'existe pas en base.");
	    }
	    
	    // 3. Si on arrive ici, c'est qu'elle existe ! On peut supprimer.
	    personRepository.deleteById(id);
	}
	

    // Mettre à jour une personne
    public Person updatePerson(Long id, Person personDetails) throws ServiceException {
    	
    	if(id <= 0) {
    		
    		throw new ServiceException("Impossible de modifier la personne : l'ID doit etre > 0");
    	}
    	
    	
        Optional<Person> person = personRepository.findById(id);
        
        if (person.isPresent()) {
        	
        	  Person existingPerson = person.get();
      		existingPerson.setCity(personDetails.getCity());
      		existingPerson.setPhonenumber(personDetails.getPhonenumber());
      		
      		return personRepository.save(existingPerson);
        }
        
        throw new ServiceException("Impossible de modifier la personne : Veuillez entrer un ID");
    }
    
    
    // Supprimer toutes les personnes
    public void deleteAllPerson() {
    	
    	 personRepository.deleteAll();
    	 
    }
}