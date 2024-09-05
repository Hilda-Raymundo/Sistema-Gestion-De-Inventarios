import java.util.Scanner;

public class Login{

	public static void main(String[] args){
	
		//Acá se declaran los identificadores para el sistema
		Scanner scan = new Scanner(System.in);
		String usuario;
		String contrasenia;

		//Acá se lee el usuario y contraseña
		System.out.println("SISTEMA DE GESTION DE INVENTARIOS");

		do{
			System.out.println(" Ingrese su nombre de usuario: ");
			usuario = scan.nextLine();
		}while(usuario == "");

		do{
			System.out.println(" Ingrese su contraseña: ");
			contrasenia = scan.nextLine();
		}while(contrasenia == "");

		System.out.println(usuario + contrasenia);

		if (usuario == "Hilda") {
			System.out.println("Bienvenido al sistema");
		}
		else if (usuario != "Hilda") {
			System.out.println("Usuario o contraseña incorrectas");
		}
	}
}