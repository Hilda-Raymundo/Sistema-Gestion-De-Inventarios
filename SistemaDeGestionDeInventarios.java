/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema.de.gestion.de.inventarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author hraym
 */
public class SistemaDeGestionDeInventarios {

    Scanner scan = new Scanner(System.in);

    public void eliminarArchivos(String nombreArchivo, String nombreCategoria) {
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            File fc = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
            FileWriter fw = new FileWriter(fc);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea = "";

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split("\\|");
                if (datos[0].compareTo(nombreCategoria) != 0) {
                    bw.write(linea + "\n");
                } else {
                    System.out.println("Está seguro de eliminar: " + nombreCategoria + "? si/no");
                    String confirmacion = scan.next();
                    if (confirmacion.equals("no")) {
                        bw.write(linea + "\n");
                    }
                }
            }
            br.close();
            bw.close();

            Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificarArchivos(String nombreArchivo, String nombreCategoria, String nuevoNombre, String descCategoria, String tipoDato) {
        try {
            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            File fc = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
            FileWriter fw = new FileWriter(fc);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea = "";

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].compareTo(nombreCategoria) == 0) {
                    if (datos[0].compareTo(nuevoNombre) != 0) {
                        if (!descCategoria.equals("")) {
                            if(nombreArchivo.equals("especificaciones.txt")){
                                linea = (nuevoNombre + "|" + descCategoria + "|" + tipoDato);
                            }else{
                            linea = (nuevoNombre + "|" + descCategoria);
                            }
                        } else {
                            linea = (nuevoNombre + "|" + datos[1]);
                        }
                        System.out.println("Modificacion exitosa");
                    } else {
                        System.out.println("El nombre de la categoria ya existe");
                    }
                }
                bw.write(linea + "\n");
            }
            bw.close();
            br.close();

            Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarArchivos(String nombreArchivo, String nombre, String descripcion, String tipoDato) {
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
        String existe = "";

        if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt")) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String leerLineas = "";

                while ((leerLineas = br.readLine()) != null) {

                    String[] datos = leerLineas.split("\\|");
                    if (datos[0].compareTo(nombre) == 0) {
                        existe = "yaExiste";
                    }

                }

                if (existe.equals("yaExiste")) {
                    System.out.println("El nombre ya se encuentra registrado");
                } else {
                    FileWriter fw = new FileWriter(f, true);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        if(nombreArchivo.equals("especificaciones.txt")){
                            bw.write(nombre + "|" + descripcion + "|" + tipoDato + "\n");
                        }else{
                            bw.write(nombre + "|" + descripcion + "\n");
                        }
                        bw.close();
                        System.out.println("Registro exitoso");
                    }
                }
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void leerArchivos(String nombreArchivo, String nombreUsuario, String contrasenia) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);

        if (nombreArchivo.equals("usuarios.txt")) {
            try {
                FileReader fr = new FileReader(f);
                try (BufferedReader br = new BufferedReader(fr)) {

                    String linea = "";

                    while ((linea = br.readLine()) != null) {
                        String[] datos = linea.split("\\|");
                        if (datos[0].equals(nombreUsuario) && datos[1].equals(contrasenia)) {
                            if (datos[2].equals("Admin")) {
                                operaciones.menuAdmin();
                            }
                            if (datos[2].equals("Encargado")) {
                                operaciones.menuEncargado();
                                break;
                            }
                            if (datos[2].equals("Vendedor")) {
                                operaciones.menuVendedor();
                                break;
                            }
                        }
                    }
                }
                fr.close();

            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt")) {

            try {
                FileReader fr2 = new FileReader(f);
                BufferedReader br = new BufferedReader(fr2);

                String leerLinea = "";

                while ((leerLinea = br.readLine()) != null) {
                    System.out.println(leerLinea);

                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void login() {
        String fileUsuarios = "usuarios.txt";
        String nombreUsuario = "";
        String contrasenia = "";

        while (nombreUsuario.equals("") || contrasenia.equals("")) {
            System.out.println("        SISTEMA DE GESTION DE INVENTARIOS");
            System.out.println("Ingrese su nombre de usuario: ");
            nombreUsuario = scan.nextLine();
            System.out.println("Ingrese su contraseña: ");
            contrasenia = scan.nextLine();
        }
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        operaciones.leerArchivos(fileUsuarios, nombreUsuario, contrasenia);
    }

    public void menuAdmin() {

        int eleccionMenuPrincipal = 0;
        int eleccionMenuSecundario = 0;

        System.out.println("			Bienvenido Administrador \n");

        while (eleccionMenuPrincipal != 3) {

            System.out.println("Este es su menú de opciones:");
            System.out.println(" 1. Informes y estadísticas ");
            System.out.println(" 2. Añadir usuarios ");
            System.out.println(" 3. Salir ");

            eleccionMenuPrincipal = scan.nextInt();

            switch (eleccionMenuPrincipal) {
                case 1:
                    System.out.println("Sus opciones son... \n");
                    System.out.println("1. Generar informe del inventario actual");
                    System.out.println("2. Generar informe de los movimientos de stock");
                    System.out.println("3. Generar informe de compras");
                    System.out.println("4. Ver gráficas y estadísticas");
                    System.out.println("5. Salir");

                    eleccionMenuSecundario = scan.nextInt();

                    break;
                case 2:
                    System.out.println("Ingrese los datos del nuevo usuario \n");
                    System.out.println("Inrese el nombre de usuario: ");
                    System.out.println("Ingrese el password: ");

                    eleccionMenuSecundario = scan.nextInt();

                    break;
            }
        }

    }

    public void menuGestiones(String nombreArchivo) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        int eleccionMenuTerciario = 0;
        String nombreCategoria = "";
        String descCategoria = "";
        String nuevoNombre = "";
        String tipoDato = "";
        do {
            System.out.println("\n Elija la operación que desea realizar: ");
            System.out.println("1. Definir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Salir");

            eleccionMenuTerciario = scan.nextInt();

            switch (eleccionMenuTerciario) {
                case 1:
                    while (nombreCategoria.equals("")) {
                        System.out.println("Ingrese: \n");
                        System.out.println("Nombre: ");
                        nombreCategoria = scan.next();
                    }
                    System.out.println("Descripción: ");
                    descCategoria = scan.next();
                    if(nombreArchivo.equals("especificaciones.txt")){
                        System.out.println("Ingrese el tipo de dato: ");
                        tipoDato = scan.next();
                    }
                    operaciones.llenarArchivos(nombreArchivo, nombreCategoria, descCategoria, tipoDato);
                    nombreCategoria = "";
                    break;
                case 2:
                    while (nombreCategoria.equals("")) {
                        System.out.println("Ingrese: \n");
                        System.out.println("Nombre del registro a modificar: ");
                        nombreCategoria = scan.next();
                        System.out.println("Nuevo nombre: ");
                        nuevoNombre = scan.next();
                    }
                    System.out.println("Descripción: ");
                    descCategoria = scan.next();
                    if(nombreArchivo.equals("especificaciones.txt")){
                        System.out.println("Ingrese el tipo de dato: ");
                        tipoDato = scan.next();
                    }
                    operaciones.modificarArchivos(nombreArchivo, nombreCategoria, nuevoNombre, descCategoria, tipoDato);
                    nombreCategoria = "";
                    nuevoNombre = "";
                    break;
                case 3:
                    while (nombreCategoria.equals("")) {
                        System.out.println("Ingrese el nombre de el registro a eliminar");
                        nombreCategoria = scan.next();
                    }
                    operaciones.eliminarArchivos(nombreArchivo, nombreCategoria);
                    nombreCategoria = "";
                    break;
                default:
                    break;
            }

        } while (eleccionMenuTerciario != 4);
    }

    public void menuEncargado() {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        int eleccionMenuPrincipal = 0;
        int eleccionMenuSecundario = 0;

        String nombreArchivo = "";

        System.out.println("			Bienvenido Encargado de bodega \n");
        System.out.println("Este es su menú de opciones:");
        System.out.println("  1. Gestionar productos");
        System.out.println("  2. Controlar existencias");
        System.out.println("  3. Salir");
        eleccionMenuPrincipal = scan.nextInt();

        switch (eleccionMenuPrincipal) {

            case 1:
                System.out.println("Sus opciones son... \n");
                System.out.println("1. Categorias de Productos");
                System.out.println("2. Caracteristicas de Productos");
                System.out.println("3. Gestionar especificaciones");
                System.out.println("4. Asignar categoria/caracteristica/especificaciones a productos");
                System.out.println("5. Alta de un producto");
                System.out.println("6. Baja de un producto");
                System.out.println("7. Modificar producto");
                System.out.println("8. Salir");
                eleccionMenuSecundario = scan.nextInt();

                switch (eleccionMenuSecundario) {
                    case 1:
                        nombreArchivo = "categorias.txt";
                        //mostrar categorias existentes
                        System.out.println("		CATEGORIA DE PRODUCTOS");
                        System.out.println("Este es el listado de categorías existentes: \n");
                        operaciones.leerArchivos(nombreArchivo, "", "");

                        operaciones.menuGestiones(nombreArchivo);

                        break;
                    case 2:
                        nombreArchivo = "caracteristicas.txt";
                        System.out.println("            CARACTERISTICA DE PRODUCTOS");
                        System.out.println("Este es el listado de caracteristicas existentes: \n");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        operaciones.menuGestiones(nombreArchivo);
                        break;
                    case 3:
                        nombreArchivo = "especificaciones.txt";
                        System.out.println("            CARACTERISTICAS DE ESPECIFICACIONES");
                        System.out.println("Este es el listado de especificaciones existentes: \n");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        operaciones.menuGestiones(nombreArchivo);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }

                break;

        }
    }

    public void menuVendedor() {
        int eleccionMenuPrincipal = 0;

        System.out.println("			Bienvenido Vendedor \n");
        System.out.println("Este es su menú de opciones:");
        System.out.println("1. Crear un pedido");
        System.out.println("2. Gestionar un pedido");
        System.out.println("3. Recepción de pedidos");
        System.out.println("4. Salir");
        eleccionMenuPrincipal = scan.nextInt();

        switch (eleccionMenuPrincipal) {

            case 1:
                System.out.println("Para crear un pedido, ingrese: \n");
                System.out.println("Proveedor: ");
                System.out.println("Productos: ");
                System.out.println("Cantidad por producto: ");
                //calcular total
                System.out.println("Confirmar su pedido? ");
                //Guardar pedido
                //Generar código único para pedido
                System.out.println("Pedido creado con éxito");
                break;
            case 2:
                //Mostrar lista de todos los pedidos
                System.out.println("Para gestionar un pedido, ingrese: \n");
                System.out.println("filtre proveedor/fecha/estado/producto: ");
                System.out.println("codigo de pedido de compra: ");
                System.out.println("Quiere modificar el pedido? si/no ");
                System.out.println("Quiere cancelar el pedido? si/no ");
                break;
            case 3:
                System.out.println("Para marcar la recepción de un pedido, ingrese: \n");
                System.out.println("Codigo pedido: ");
                System.out.println("Cantidad de productos recibidos: ");
                //verificar si la cantidad es la correcta
                //Actualizar cantidad en stock
                //registrar la recepcion
                break;
        }
    }

    public static void main(String[] args) {

        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        operaciones.login();

    }

}
