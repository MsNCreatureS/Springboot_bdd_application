package com.erwan.Springboot_bdd_application.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String city;
	
	private String phoneNumber;
	
	// La relation : Plusieurs personnes peuvent travailler pour UNE société
    @ManyToOne
    @JoinColumn(name = "company_id") // C'est le nom de la colonne qui sera créée dans ta table SQL
    @JsonBackReference
    private Company company;
    
    
    // Realtion avec les skills 
    
    @ManyToMany
    @JoinTable(
            name = "person_skills", // Nom de la table intermédiaire
            joinColumns = @JoinColumn(name = "person_id"), // Clé de la personne
            inverseJoinColumns = @JoinColumn(name = "skill_id") // Clé de la compétence
        )
    private List<Skill> skills;
    
    
	// LE CONSTRUCTEUR VIDE (Indispensable pour Hibernate)
    public Person() {
    }
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhonenumber() {
		return phoneNumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	

}
