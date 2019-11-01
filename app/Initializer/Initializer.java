package Initializer;

import java.util.Date;

import models.Conta;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Initializer extends Job{
	@Override
	public void doJob() throws Exception{
		if(Usuario.count()== 0){
			Usuario usuario1 = new Usuario();
			usuario1.nome = "adm";
			usuario1.email="adm@123";
			usuario1.senha ="123";
			usuario1.save();
			
			Conta conta = new Conta();
			conta.pontuacao = 0;
			conta.dateAtual = new Date();
			conta.save();
			
			usuario1.conta = conta;
			usuario1.save();
			
			Usuario usuario2 = new Usuario();
			usuario2.nome = "mara";
			usuario2.email="jogador@123";
			usuario2.senha ="jogador";
			usuario2.save();
			
			Conta conta1 = new Conta();
			conta1.pontuacao = 0;
			conta1.dateAtual = new Date();
			conta1.save();
			
			usuario2.conta = conta1;
			usuario2.save();

			
		}
	}
	
	

}
