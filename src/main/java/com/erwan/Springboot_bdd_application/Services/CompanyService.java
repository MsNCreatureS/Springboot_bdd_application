package com.erwan.Springboot_bdd_application.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.erwan.Springboot_bdd_application.Exceptions.ServiceException;
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
	
	// Crée une compagnie
	
	public Company createCompany(Company company) {
		
		return companyRepository.save(company);

	}
	
	
	
	// Trouver via ID
	
	public Optional<Company> getComanyById(Long id) throws ServiceException {
		
		if(id <= 0) {
			
			throw new ServiceException("Impossible de trouver la compant : l'ID doit etre > 0");
		}
		
		Optional<Company> company = companyRepository.findById(id);
		
		if(company.isPresent()) {
			
			return companyRepository.findById(id);
		}
		
		throw new ServiceException("La company existe pas");
		
	}
	
	
	// supprimer une compagnie 
	
	public void deleteCompany(Long id) throws ServiceException {
		
		if (id <= 0) {
			
			throw new ServiceException("Impossible de supprimer la company : l'ID doit etre > 0");
		}
		
		
		if(!companyRepository.existsById(id)) {
			
			throw new ServiceException("Impossible de supprimer la company : Elle n'existe pas en base.");
		}
		
		companyRepository.deleteById(id);
		
		
	}

}
