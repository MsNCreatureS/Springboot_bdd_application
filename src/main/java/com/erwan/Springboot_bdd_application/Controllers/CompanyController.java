package com.erwan.Springboot_bdd_application.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwan.Springboot_bdd_application.Exceptions.ServiceException;
import com.erwan.Springboot_bdd_application.Models.Company;
import com.erwan.Springboot_bdd_application.Services.CompanyService;

@RestController
@RequestMapping("api/companys")
public class CompanyController {
	
	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		
		this.companyService = companyService;
	}
	
	
	// Creation d'une comapgnie
	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		
		Company companyCreated = companyService.createCompany(company);
		
		return new ResponseEntity<>(companyCreated, HttpStatus.CREATED);
		
	}
	
	
	//Trouver une company via ID
	@GetMapping("/{id}")
	public ResponseEntity<Object> getComanyById(@PathVariable Long id) {
		
		try {
			
			Optional<Company> company = companyService.getComanyById(id);
			
			return new ResponseEntity<>(company.get(), HttpStatus.OK);
			
		} catch (ServiceException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
