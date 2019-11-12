package controllers;


import java.util.Date;

import java.util.HashMap;
import java.util.Map;


import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.google.gson.Gson;
import com.google.gson.JsonElement;


import models.Conta;
import models.Usuario;


import play.data.validation.Valid;
import play.libs.Crypto;
import play.libs.Mail;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;



public class Usuarios extends Controller{
	
	public static void cadastrousuario(Usuario usuario){
		render(usuario);
		
	}
	
	
	
	public static void recuperaSenha(String Email){
		render(Email);
	}
	
	//consumido pelo cliente
	public static void salvarUsuario(String nome, String email, String senha){
		Usuario u = new Usuario();
		Conta conta = new Conta();
		conta .save();
		u.nome=nome;
		u.email=email;
		u.senha=senha;		
		u.conta = conta;
		u.save();			
		ok();
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
		ok();
		 	
	}
	
	
	public static void atualizardados(Usuario usuario){
		long id = new Long(session.get("idUsuarioLogado"));
		usuario = Usuario.findById(id);
		render(usuario);
	}
	
	public static void editar(@Valid Usuario usuario, String confirmaSenha){
		System.out.println(validation.hasErrors());
		if(validation.hasErrors()){
			validation.keep();
			params.flash();
			atualizardados(usuario);
		}
		usuario.save();
		flash.success("Dados atualizados com Sucesso!");
		renderTemplate("Application/index.html", usuario);
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
	
	public static void detalhes(Usuario usuario){
		render(usuario); 
	}
	
	

	
	
    
    
}
	


