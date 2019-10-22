package controllers;

import javax.persistence.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.Usuario;

import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Controller;

@Entity
public class Usuarios extends Controller{
	public static void usuario(Usuario usuario){
		render(usuario);
	}
	
	
	public static void salvar(@Valid Usuario usuario, String confirmarSenha){
		if(usuario.isUnico()){
			if(usuario.senha.equals(confirmarSenha)){
				if(validation.hasErrors()){
					validation.keep();
					params.flash();
					usuario(usuario);
				}
				
				usuario.save();
				flash.success("Conta Criada com Sucesso!");
				renderTemplate("Logins/login.html",usuario);
			}else{
				flash.error("Senhas não são iguais");
				usuario(usuario);
			}
			
		}else{
			flash.error("Este E-mail já está em uso");
			usuario(usuario);
		}
	}
	
	
}
