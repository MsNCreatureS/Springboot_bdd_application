package com.erwan.Springboot_bdd_application.Services;

import org.springframework.stereotype.Service;

import com.erwan.Springboot_bdd_application.Models.Company;
import com.erwan.Springboot_bdd_application.Repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		
		this.companyRepository = companyRepository;
	}
	
	public Company createCompany(Company company) {
		
		return companyRepository.save(company);

	}

}
