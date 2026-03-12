package com.erwan.Springboot_bdd_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erwan.Springboot_bdd_application.Models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
