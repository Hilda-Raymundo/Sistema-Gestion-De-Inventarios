import java.util.Scanner;

/**
* Esta clase contiene el login para roles de usuario del sistema de gestion de inventarios
* @author Hilda Aileen Raymundo Diaz
* @since 04/09/2024
**/

public class Login{

	public static void main(String[] args){
	
		//Acá se declaran los identificadores para el sistema
		Scanner scan = new Scanner(System.in);
		String usuario = "";
		String contrasenia = "";

		System.out.println("SISTEMA DE GESTION DE INVENTARIOS");

		//Acá se lee el usuario y contraseña
		while(usuario == "" || contrasenia == ""){
			System.out.println(" Ingrese su nombre de usuario: ");
			usuario = scan.nextLine();
			System.out.println(" Ingrese su contraseña: ");
			contrasenia = scan.nextLine();

			if (usuario.equals("Hilda") && contrasenia.equals("Hilda")) {
				System.out.println("Bienvenido al sistema");
			}
			else {
				System.out.println("Usuario o contraseña incorrecta :C");
			}
			
		}
	}
}