package com.erwan.Springboot_bdd_application.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erwan.Springboot_bdd_application.Models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	Optional<Person> findByName(String name);
	
}
