package com.erwan.Springboot_bdd_application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erwan.Springboot_bdd_application.Models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
