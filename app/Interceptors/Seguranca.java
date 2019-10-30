package Interceptors;



import controllers.Logins;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	//before indica que estou chamando esse meodo primeiro e verifica se esta logado ou nao
	@Before(unless="login")
	public void Autenticar(){
		if(!session.contains("usuario")){
			flash.error("Efetue seu Login no Sistema!");
			Logins.login();
		}
	}

}
