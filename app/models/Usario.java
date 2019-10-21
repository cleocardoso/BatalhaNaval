package models;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

public class Usario extends Model{
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
