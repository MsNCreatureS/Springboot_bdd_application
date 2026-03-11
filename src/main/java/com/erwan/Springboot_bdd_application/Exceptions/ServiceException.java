package com.erwan.Springboot_bdd_application.Exceptions;

// On dit à Java : "Cette classe est une Exception (une alarme)"
public class ServiceException extends Exception {

	// On crée un constructeur qui accepte un message personnalisé
	public ServiceException(String message) {
		// On envoie ce message à la classe parente (Exception) pour qu'elle le garde en mémoire
		super(message);
	}
	
}