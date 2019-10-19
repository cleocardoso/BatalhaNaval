package controllers;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.mvc.Controller;

@Entity
public class Usuarios extends Controller{
	@Required
	String nome;
	
	@Required
	String email;
	
	@Required
	String senha;
	
	@Temporal(TemporalType.DATE)
	String dateAtual;
	
	@Required
	int pontucao;
	
}
