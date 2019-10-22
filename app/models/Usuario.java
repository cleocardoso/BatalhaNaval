package models;

import javax.persistence.Temporal;

import javax.persistence.TemporalType;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

public class Usuario extends Model{
	@Required
	public String nome;
	
	@Required
	@Email
	public String email;
	
	@Required
	public String senha;
	
	@Temporal(TemporalType.DATE)
	public String dateAtual;
	
	@Required
	public int pontucao;

	public boolean isUnico() {
		Usuario u = Usuario.find("email", email).first();
		if(u == null || u.id == id){
			return true;
		}
		return false;
	}
}
