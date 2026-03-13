package com.erwan.Springboot_bdd_application.Services;

import org.springframework.stereotype.Service;

import com.erwan.Springboot_bdd_application.Models.Skill;
import com.erwan.Springboot_bdd_application.Repository.SkillRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SkillService {
	
	private final SkillRepository skillRepository;
	
	public SkillService(SkillRepository skillRepository) {
		
		this.skillRepository = skillRepository;
		
	}
	
	// crée un skill
	
	public Skill createSkill(Skill skill) {
		
		return skillRepository.save(skill);
	}
	
	

}
