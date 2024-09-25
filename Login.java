import java.util.Scanner;
import java.io.*;

/**
* Esta clase contiene el login para roles de usuario del sistema de gestion de inventarios
* @author Hilda Aileen Raymundo Diaz
* @since 04/09/2024
**/

public class Login{

	//Inicio del método principal
	public static void main(String[] args){
	
		//Esto es la declaracion de los identificadores para el sistema
		Scanner scan = new Scanner(System.in);
		String usuario = "";
		String contrasenia = "";
		int seleccion = 0;
		
		System.out.println("		SISTEMA DE GESTION DE INVENTARIOS");

		//Evaluacion del llenado de los campos usuario y contraseña
		while(usuario == "" || contrasenia == ""){
			System.out.println("\n Ingrese su nombre de usuario: ");
			usuario = scan.nextLine();
			System.out.println(" Ingrese su contraseña: ");
			contrasenia = scan.nextLine();

			//Inicio del Login, evalua si el usuario y contraseña son correctos
			if (usuario.equals("Admin") && contrasenia.equals("1234")) {
 				menuAdministrador();
			}
			else if(usuario.equals("Vendedor") && contrasenia.equals("5678")){
				menuVendedor();
			}
			else if(usuario.equals("Encargado") && contrasenia.equals("9123")){	
				menuEncargado();
			}
			else{	
				System.out.println("Usuario o contraseña incorrecta :C");
			}
		}
	}
	//Fin del método principal

	//Este metodo muestra el menu del administrador
	public static void menuAdministrador(){
		Scanner scan = new Scanner(System.in);
		int eleccion = 0;
		int seleccion = 0;

		limpiar();

		while(eleccion != 3){

			System.out.println("			Bienvenido Administrador \n");
			System.out.println("Este es su menú de opciones:");
				System.out.println(" 1. Informes y estadísticas ");
				System.out.println(" 2. Añadir usuarios ");
				System.out.println(" 3. Salir ");
		
				eleccion = scan.nextInt();	

				//Se limpia la pantalla
				limpiar();

				//Se evalúa la elección de el usuario realiza las acciones correspondientes
				if(eleccion == 1){

					while(seleccion != 5){
						System.out.println("Sus opciones son... \n");
						System.out.println("1. Generar informe del inventario actual");				
						System.out.println("2. Generar informe de los movimientos de stock");
						System.out.println("3. Generar informe de compras");
						System.out.println("4. Ver gráficas y estadísticas");
						System.out.println("5. Salir");

						seleccion = scan.nextInt();
						
						if(seleccion == 1){
							limpiar();
							System.out.println("Para generar un informe, ingrese: \n");
							System.out.println("categoria: ");
							System.out.println("ordenar por: (nombre del producto, cantidad de stock, valor total de stock)");
							System.out.println("Quiere exportar el informe? si/no ");
						}

						if(seleccion == 2){
							limpiar();
							System.out.println("Para generar un informe de los movimientos de stock, ingrese: \n");
							System.out.println("nombre producto: ");
							System.out.println("rango de fecha: ");
							System.out.println("Quiere exportar el informe? si/no ");
						}				

						if(seleccion == 3){
							limpiar();
							System.out.println("Para generar un informe de compras, ingrese: \n");
							System.out.println("nombre proveedor: ");
							System.out.println("rango de fecha: ");
							System.out.println("Quiere exportar el informe? si/no ");
						}

						if(seleccion == 4){
							limpiar();
							System.out.println("Para ver gráficas y estadísticas, ingrese: \n");
							System.out.println("producto/categoria/proveedor/rango de fecha: ");
						}

					}

				}else if(eleccion == 2){

					//while(seleccion != 1){
					
						//System.out.println("Ingrese los datos del nuevo usuario \n");
						//System.out.println("Inrese el nombre de usuario: ");				
						//System.out.println("Ingrese el password: ");
						

						//seleccion = scan.nextInt();
				
					//}

				}
		}
	}


	//Este metodo muestra el menu del vendedor
	public static void menuVendedor(){
			
		limpiar();
			
		Scanner scan = new Scanner(System.in);
		int eleccion = 0;
		int seleccion = 0;

		limpiar();

		while(eleccion != 2){

			System.out.println("			Bienvenido Vendedor \n");
			System.out.println("Este es su menú de opciones:");
			System.out.println("  1. Pedidos de compra");
			System.out.println("  2. Salir");
		
			eleccion = scan.nextInt();	

			//Se limpia la pantalla
			limpiar();

				//Se evalúa la elección de el usuario realiza las acciones correspondientes
				if(eleccion == 1){

					while(seleccion != 4){
						System.out.println("Sus opciones son... \n");
						System.out.println("1. Crear un pedido");				
						System.out.println("2. Gestionar un pedido");
						System.out.println("3. Recepción de pedidos");
						System.out.println("4. Salir");

						seleccion = scan.nextInt();
						
						if(seleccion == 1){
							limpiar();
							System.out.println("Para crear un pedido, ingrese: \n");
							System.out.println("Proveedor: ");
							System.out.println("Productos: ");
							System.out.println("Cantidad por producto: ");
							//calcular total
							System.out.println("Confirmar su pedido? ");
							//Guardar pedido
							//Generar código único para pedido
							System.out.println("Pedido creado con éxito");
						}

						if(seleccion == 2){
							limpiar();
							//Mostrar lista de todos los pedidos
							System.out.println("Para gestionar un pedido, ingrese: \n");
							System.out.println("filtre proveedor/fecha/estado/producto: ");
							System.out.println("codigo de pedido de compra: ");
							System.out.println("Quiere modificar el pedido? si/no ");
							System.out.println("Quiere cancelar el pedido? si/no ");
						}				

						if(seleccion == 3){
							limpiar();
							System.out.println("Para marcar la recepción de un pedido, ingrese: \n");
							System.out.println("Codigo pedido: ");
							System.out.println("Cantidad de productos recibidos: ");
							//verificar si la cantidad es la correcta
							//Actualizar cantidad en stock
							//registrar la recepcion
						}
					}

				}
			}
	}


	//Este metodo muestra el menu del encargado de bodega
	public static void menuEncargado(){

		limpiar();
			
		Scanner scan = new Scanner(System.in);
		int eleccion = 0;
		int seleccion = 0;

		while(eleccion != 3){

			System.out.println("			Bienvenido Encargado de bodega \n");
			System.out.println("Este es su menú de opciones:");
			System.out.println("  1. Gestionar productos");			
			System.out.println("  2. Controlar existencias");
			System.out.println("  3. Salir");
		
			eleccion = scan.nextInt();	

			//Se limpia la pantalla
			limpiar();

				//Se evalúa la elección de el usuario realiza las acciones correspondientes
				if(eleccion == 1){

					while(seleccion != 8){
						System.out.println("Sus opciones son... \n");
						System.out.println("1. Definir categoria");				
						System.out.println("2. Definir caracteristica");
						System.out.println("3. Definir especificaciones");
						System.out.println("4. Asignar categoria/caracteristica/especificaciones a productos");
						System.out.println("5. Alta de un producto");
						System.out.println("6. Baja de un producto");
						System.out.println("7. Modificar producto");
						System.out.println("8. Salir");

						seleccion = scan.nextInt();
						
						if(seleccion == 1){
							limpiar();
							//mostrar categorias existentes
							//preguntar si quiere definir o modificar categoria o eliminar
							System.out.println("Para definir una categoría, ingrese: \n");
							System.out.println("Nombre categoría: ");
							System.out.println("Descripción categoría: ");
							//Guardar la nueva categoría
						}

						if(seleccion == 2){
							limpiar();
							//mostrar caracteristicas existentes
							//preguntar si quiere definir o modificar caracteristica o eliminar
							System.out.println("Para definir una característica, ingrese: \n");
							System.out.println("Nombre característica: ");
							System.out.println("Descripción característica: ");
							//Guardar la nueva caracteristica
						}				

						if(seleccion == 3){
							limpiar();
							//mostrar especificacion existentes
							//preguntar si quiere definir o modificar especificacion o eliminar
							System.out.println("Para definir una especificacion, ingrese: \n");
							System.out.println("Nombre especificacion: ");
							System.out.println("Descripción especificacion: ");
							System.out.println("Tipo dato: ");
							//Guardar la nueva especificacion
						}
						if(seleccion == 4){
							limpiar();
							//preguntar si quiere asignar o modificar asignacion
							System.out.println("Para asignaciones, ingrese: \n");
							System.out.println("Seleccione categoría");
							System.out.println("Seleccione características");
							System.out.println("Seleccione especificaciones");
						}				

						if(seleccion == 5){
							limpiar();
							System.out.println("Para dar de alta un producto, ingrese: \n");
							System.out.println("Nombre producto: ");
							System.out.println("Seleccione categoría");
							System.out.println("Ingrese descripcion");
							System.out.println("Ingrese precio de venta");
							System.out.println("Ingrese cantidad inicial en stock");
							//generar identificador para el producto
							//guardar	
							System.out.println("Producto creado correctamente");
						}
						if(seleccion == 6){
							limpiar();
							System.out.println("Para dar baja a un producto, ingrese: \n");	
							System.out.println("Seleccione producto");
							System.out.println("Seguro? si/no ");
						}				

						if(seleccion == 7){
							limpiar();
							System.out.println("Para modificar un producto, ingrese: \n");
							System.out.println("Seleccione producto");
							//mostrar datos actuales
							//recibir nuevos datos
							//validar y guardar
							System.out.println("Producto modificado correctamente");
						}
					}

				}

				//Se evalúa la elección de el usuario realiza las acciones correspondientes
				if(eleccion == 2){

					while(seleccion != 3){
						System.out.println("Sus opciones son... \n");
						System.out.println("1. Registro de entrada de Productos");				
						System.out.println("2. Registro de salida de Productos");
						System.out.println("3. Historial de movimientos en stock");
						System.out.println("4. Salir");

						seleccion = scan.nextInt();
						
						if(seleccion == 1){
							limpiar();
							System.out.println("Para registrar la entrada de productos, ingrese: \n");
							System.out.println("Ingresar codigo del producto");
							//buscar producto
							System.out.println("Cantidad del producto:");
							//Actualizar cantidad
						}

						if(seleccion == 2){
							limpiar();
							System.out.println("Para registrar la salida de productos, ingrese: \n");
							System.out.println("Ingresar codigo del producto");
							//buscar producto
							System.out.println("Cantidad del producto:");
							//Actualizar cantidad
						}				

						if(seleccion == 3){
							limpiar();
							System.out.println("Para ver un historial, ingrese: \n");
							System.out.println("Seleccionar producto");
							//Mostrar historial
						}
					}

				}
			}

	}
























	//Este método limpia la pantalla
	public static void limpiar(){
		System.out.print("\033[H\033[2J");  
		System.out.flush();
	}












































	//se llama a la clase file para pasarle un archivo
	File archivo;
	private void crearArchivoDeTexto(){
				
		archivo = new File("login.txt");
		try{
			if(archivo.createNewFile()){
				System.out.println("Creacion exitosa");
			}
			else{
				//System.out.println("Error al crear archivo");
			}
		}catch(IOException exepcion){
			exepcion.printStackTrace(System.out);

		}	
	}



















	//Método para eliminar archivos
	private void eliminarArchivoDeTexto(){
				
			if(archivo.delete()){
				System.out.println("Eliminación exitosa");
			}
			else{
				//System.out.println("Error al eliminar archivo");
			}
	}


















	//Metodo para escribir en archivos
	private void escribirEnArchivosDeTexto(){
				
		try{
			FileWriter escritura  = new FileWriter(archivo);
			escritura.write("Saludos");
			escritura.close();
			System.out.println("Texto añadido");
		}catch(IOException exepcion){
			exepcion.printStackTrace(System.out);
		}

	}















	//Metodo para leer en un archivo de texto
	private void leerArchivoDeTexto(){
				
		String contenido;

		try{
			FileReader lectura  = new FileReader(archivo);
			BufferedReader lector = new BufferedReader(lectura);
			contenido = lector.readLine();

			while(contenido != null){
				System.out.println(contenido);
				contenido = lector.readLine();
			}

			
		}catch(IOException exepcion){
			exepcion.printStackTrace(System.out);
		}

	}











}