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
		int seleccion = 0;

		System.out.println("SISTEMA DE GESTION DE INVENTARIOS");

		//Acá se lee el usuario y contraseña
		while(usuario == "" || contrasenia == ""){
			System.out.println(" Ingrese su nombre de usuario: ");
			usuario = scan.nextLine();
			System.out.println(" Ingrese su contraseña: ");
			contrasenia = scan.nextLine();

			if (usuario.equals("Hilda") && contrasenia.equals("Hilda")) {

				System.out.print("\033[H\033[2J");  
				System.out.flush(); 

				System.out.println("Bienvenido al sistema \n");
				System.out.println("Este es el menú de opciones: ");
				System.out.println(" 1. Gestion de productos");
				System.out.println(" 2. Control de existencias ");
				System.out.println(" 3. Pedidos de compra");
				System.out.println(" 4. Informes y estadisticas");
				System.out.println(" 5. Salir \n");
				seleccion = scan.nextInt();
							
				switch(seleccion){

				case 1:
					System.out.println(" 			MENU DE OPCIONES \n");
					System.out.println("1. Definicion de categorias");
					System.out.println("2. Definicion de caracteristicas");
					System.out.println("3. Definicion de especificaciones");
					System.out.println("4. Asignación de Categorías, Características y Especificaciones a Productos");
					System.out.println("5. Alta de productos");
					System.out.println("6. Baja de productos ");
					System.out.println("7. Modificacion de productos");
					System.out.println("8. Salir");
					break;
				case 2:
					System.out.println(" 			MENU DE OPCIONES \n");					System.out.println("1. Registro de entrada de productos");
					System.out.println("2. Registro de salida de productos");
					System.out.println("3. Movimientos de stock");
					System.out.println("4. Salir");
					break;
				case 3:
					System.out.println(" 			MENU DE OPCIONES \n");
					System.out.println("1. Creacion de pedido de compra");
					System.out.println("2. Gestion de pedidos de compra");
					System.out.println("3. Recepcion de pedidos de compra");
					System.out.println("4. Salir");
					break;
				case 4:
					System.out.println(" 			MENU DE OPCIONES \n");
					System.out.println("1. Generacion de informes de inventario actual");
					System.out.println("2. Generacion de informes de movimientos en stock");
					System.out.println("3. Generacion de informes de compras");
					System.out.println("4. Ver graficas y estadisticas");
					System.out.println("5. Salir");
					break;
				default:
					System.out.println("Opcion no existente");
				}		

			}
			else {
				System.out.println("Usuario o contraseña incorrecta :C");
			}
			
		}
	}
}