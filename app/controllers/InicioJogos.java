package controllers;

import java.util.Date;

import java.util.List;

import models.IniciaJogo;
import models.Usuario;
import play.mvc.Controller;

public class InicioJogos extends Controller{
	
	public static void inicioJogo(){
		long idAdm = new Long(session.get("idUsuarioLogado"));
		Usuario usuario = Usuario.findById(idAdm);
		
		
		long id = new Long(session.get("idInicioJogo"));
		IniciaJogo iniciajogo = IniciaJogo.findById(id);
		iniciajogo.finalJogo = new Date();
		
		iniciajogo.save();
		render(iniciajogo,usuario);
	}
	
	public static void listagemJogador(){
		List<IniciaJogo> iniciajogos = IniciaJogo.findAll();
		render(iniciajogos);
	}

}
