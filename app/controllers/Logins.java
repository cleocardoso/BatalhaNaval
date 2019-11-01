package controllers;

import java.util.Date;

import models.IniciaJogo;
import models.Usuario;

import play.mvc.Controller;

public class Logins extends Controller{

	public static void login(){
		render();
	}
	
	public static void autenticar(String login, String senha){
		boolean tipoLogin = false;
		Usuario usuario = null;
		
		for(int i = 0; i < login.length() ; i++){
			if(login.charAt(i) == '@'){
				tipoLogin = true;
			}
		}		
				
		if(tipoLogin){
			usuario = Usuario.find("email = ? and senha = ?",login, senha).first();
		}
		//outro meio de login
		/*else{
			usuario = Usuario.find("", params)
		}*/
		
		if(usuario == null){
			flash.error("Por favor, entre com usuário e senha corretos");
			login();
		}else if(login.equals("admin@123") && senha.equals("123")){
			session.put("idUsuarioLogado", usuario.id);
			
			IniciaJogo iniciajogo = new IniciaJogo();
			iniciajogo.inicioJogo = new Date();
			iniciajogo.save();
			session.put("idInicioJogo", iniciajogo.id);
			
			Administradores.administrador();
			
		}else{
			session.put("idUsuarioLogado",  usuario.id);
			Application.index();
		}	
				
	}
	
	
	public static void logout(){
		session.clear();
		System.out.println("logout");
		login();
	}
	
	public static void logoutAdministrador(){
		/*long id = new Long(session.get("idInicioJogo"));
		IniciaJogo iniciaJogo = IniciaJogo.findById(id);
		iniciaJogo.finalJogo = new Date();
		
		iniciaJogo.save();
		
		session.clear();
		System.out.println("logout");*/
		login();
	}
}
