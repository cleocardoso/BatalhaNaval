package controllers;


import java.util.Date;


import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.Conta;
import models.Usuario;


import play.data.validation.Valid;
import play.libs.Crypto;
import play.libs.Mail;
import play.mvc.Controller;

public class Usuarios extends Controller{
		
	public static void cadastrousuario(Usuario usuario){
		render(usuario);
	}
	
	public static void recuperaSenha(String Email){
		render(Email);
	}
	
	
	public static void salvar(@Valid Usuario usuario, String confirmarSenha){
		if(usuario.isUnico()){
			if(usuario.senha.equals(confirmarSenha)){
				if(validation.hasErrors()){
					validation.keep();
					params.flash();
					cadastrousuario(usuario);
				}
				
				usuario.save();
				
				Conta conta = new Conta();
				conta.id_usuario = (usuario.id.intValue() +1);
				conta .save();
				
				usuario.conta = conta;
				usuario.save();
				
				flash.success("Conta Criada com Sucesso!");
				renderTemplate("Logins/login.html",usuario);
			}else{
				flash.error("Senhas não são iguais");
				cadastrousuario(usuario);
			}
			
		}else{
			flash.error("Este E-mail já está em uso");
			
		}
		cadastrousuario(usuario);
	}
	
	public static void dados(Usuario usuario){
		long id = new Long(session.get("idUsuarioLogado"));
		usuario = Usuario.findById(id);
		render(usuario);
	}
	
	
	public static void novaSenha(Usuario usuario) throws EmailException{
		Usuario u = Usuario.find("email = ?", usuario.email).first();
		if(u != null){
			String nvsenha = Crypto.passwordHash(new Date().toString());
			nvsenha = nvsenha.substring(0,4);
			u.senha = nvsenha;
			u.save();
			
			HtmlEmail email = new HtmlEmail();
			email.addTo(u.email, u.nome);
			email.setFrom("btnaval2019.1@gmail.com", "batalhanaval");
			email.setSubject("Uma nova Senha foi criada.");
			email.setHtmlMsg("<h1>Batalha Naval</h1><p>Sua Nova Senha"+nvsenha+".</p>");
			Mail.send(email);
			
			flash.success("Uma nova Senha foi enviada para seu Email");
			Logins.login();
			
			
		}
		
	}
	
	public static void listagem(Usuario usuario){
		List<Usuario> usaurios = Usuario.findAll();
		render(usuario);
	}
	
	
	
	
	
	
}
