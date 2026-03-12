package com.erwan.Springboot_bdd_application.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
