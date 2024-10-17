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
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hraym
 */
public class SistemaDeGestionDeInventarios {

    Scanner scan = new Scanner(System.in);

    public void eliminarArchivos(String nombreArchivo, String nombreCategoria) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        String confirmacion = "";

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

                    System.out.println("Está seguro de eliminar: " + linea + "? si/no");
                    confirmacion = scan.next();
                    if (confirmacion.equals("no")) {
                        bw.write(linea + "\n");
                    } else {
                        System.out.println("Eliminacion exitosa");
                    }
                }
            }

            br.close();
            bw.close();

            Files.move(fc.toPath(), f.toPath(), REPLACE_EXISTING);

            if ((nombreArchivo.equals("especificaciones.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("categorias.txt")) && confirmacion.equals("si")) {
                operaciones.eliminarCoincidencias("productos.txt", nombreArchivo, nombreCategoria);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarCoincidencias(String nombreArchivo, String nombreArchivoEvaluar, String nombre) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        String confirmacion = "";

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

                if (nombreArchivoEvaluar.equals("categorias.txt")) {
                    if (datos[2].compareTo(nombre) != 0) {
                        bw.write(linea + "\n");
                    } else {
                        bw.write(datos[0] + "|" + datos[1] + "|" + "" + "|" + datos[3] + "|" + datos[4] + "|" + datos[5] + "|" + datos[6] + "|" + datos[7] + "\n");
                    }
                } else if (nombreArchivoEvaluar.equals("caracteristicas.txt")) {
                    datos[3] = datos[3].replace(nombre, "");
                    bw.write(datos[0] + "|" + datos[1] + "|" + datos[2] + "|" + datos[3] + "|" + datos[4] + "|" + datos[5] + "|" + datos[6] + "|" + datos[7] + "\n");
                } else if (nombreArchivoEvaluar.equals("especificaciones.txt")) {
                    datos[4] = datos[4].replace(nombre, "");
                    bw.write(datos[0] + "|" + datos[1] + "|" + datos[2] + "|" + datos[3] + "|" + datos[4] + "|" + datos[5] + "|" + datos[6] + "|" + datos[7] + "\n");
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
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
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
                    if (datos[1].compareTo(nuevoNombre) != 0) {
                        if (!descCategoria.equals("")) {
                            if (nombreArchivo.equals("especificaciones.txt")) {
                                linea = (datos[0] + "|" + nuevoNombre + "|" + descCategoria + "|" + tipoDato);
                            } else {
                                linea = (datos[0] + "|" + nuevoNombre + "|" + descCategoria);
                            }
                        } else {
                            linea = (datos[0] + "|" + nuevoNombre + "|" + datos[2]);
                        }
                        System.out.println("Modificacion exitosa");
                    } else {
                        System.out.println("El nombre ya existe");
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

    public void buscarArchivos(String nombreArchivo, String nombre) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
        File fEntradas = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
        String linea = "";
        String existe = "";
        int cant = 0;

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].compareTo(nombre) == 0) {
                    System.out.println("Se encontro el archivo: " + linea);
                    existe = "siExiste";

                    if (nombreArchivo.equals("entradas.txt")) {
                        System.out.println("Cantidad del producto que desea ingresar: ");
                        cant = scan.nextInt();
                        String cantidad = String.valueOf(cant);
                        operaciones.llenarArchivos(nombreArchivo, nombre, cantidad, "");
                    } else if (nombreArchivo.equals("salidas.txt")) {
                        System.out.println("Cantidad del producto que desea vender: ");
                        cant = scan.nextInt();
                        String cantidad = String.valueOf(cant);
                        operaciones.llenarArchivos(nombreArchivo, nombre, cantidad, "");
                    }
                    break;
                }
            }

            if (!existe.equals("siExiste")) {
                System.out.println("El producto no esta registrado");
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarArchivos(String nombreArchivo, String nombre, String descripcion, String tipoDato) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
        String existe = "";
        int contador = 0;
        int numeracion = 0;
        String numero = "";

        if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt") || nombreArchivo.equals("entradas.txt") || nombreArchivo.equals("salidas.txt")) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String leerLineas = "";

                while ((leerLineas = br.readLine()) != null) {
                    String[] datos = leerLineas.split("\\|");
                    if (datos[1].compareTo(nombre) == 0) {
                        if (nombreArchivo.equals("entradas.txt") || nombreArchivo.equals("salidas.txt")) {

                        } else {
                            existe = "yaExiste";
                        }
                    }
                    numero = datos[0];
                }

                numeracion = parseInt(numero) + 1;

                if (existe.equals("yaExiste")) {
                    System.out.println("El nombre ya se encuentra registrado");
                } else {
                    FileWriter fw = new FileWriter(f, true);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        if (nombreArchivo.equals("especificaciones.txt")) {
                            bw.write(numeracion + "|" + nombre + "|" + descripcion + "|" + tipoDato + "\n");
                        } else if (nombreArchivo.equals("salidas.txt") || nombreArchivo.equals("entradas.txt")) {
                            Date fecha = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            String fechaParaCodigo = sdf.format(fecha);

                            bw.write(nombre + "|" + descripcion + "|" + fechaParaCodigo + "\n");
                        } else {
                            bw.write(numeracion + "|" + nombre + "|" + descripcion + "\n");
                        }
                        bw.close();
                        System.out.println("Registro exitoso");
                    }
                }
                br.close();

            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (nombreArchivo.equals("productos.txt")) {
            try {
                FileReader fr = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\categorias.txt");
                BufferedReader br = new BufferedReader(fr);

                String linea = "";
                String nombreProducto = "";
                String categoria = "";
                String valorCaracteristica = "";
                String caracteristicas = "";
                String caracteristica = "";
                String valoresCaracteristicas = "";
                String especificacion = "";
                String valorEspecificacion = "";
                String especificaciones = "";
                String valoresEspecificaciones = "";
                String esValido = "";

                System.out.println("Ingrese el nombre del producto: ");
                nombreProducto = scan.next();

                System.out.println("\n        ELIJA LA CATEGORIA");
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                System.out.println("escriba la categoria a seleccionar: ");
                categoria = scan.next();

                FileReader fr2 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\caracteristicas.txt");
                BufferedReader br2 = new BufferedReader(fr2);

                String aniadirOtraCaracteristica = "";
                while (!aniadirOtraCaracteristica.equals("no")) {
                    System.out.println("\n        ELIJA LAS CARACTERISTICAS");
                    while ((linea = br2.readLine()) != null) {
                        System.out.println(linea);
                    }
                    linea = "";
                    System.out.println("escriba la caracteristica a seleccionar: ");
                    valorCaracteristica = scan.next();

                    String otroValor = "";
                    String nuevoValor = "";
                    while (!otroValor.equals("no")) {
                        System.out.println("Ingrese el valor para la característica seleccionada: ");
                        nuevoValor = scan.next();
                        if (valoresCaracteristicas.equals("")) {
                            valoresCaracteristicas = nuevoValor;
                        } else {
                            valoresCaracteristicas = valoresCaracteristicas + "," + nuevoValor;
                        }
                        System.out.println("Agregar otro valor? si/no");
                        otroValor = scan.next();
                    }
                    caracteristicas = caracteristicas + valorCaracteristica + ":" + valoresCaracteristicas + ";";
                    valoresCaracteristicas = "";

                    System.out.println("Seleccionar otra caracteristica? si/no");
                    aniadirOtraCaracteristica = scan.next();

                }
                System.out.println(caracteristicas);

                FileReader fr3 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\especificaciones.txt");
                BufferedReader br3 = new BufferedReader(fr3);

                String aniadirOtraEspecificacion = "";
                while (!aniadirOtraEspecificacion.equals("no")) {
                    System.out.println("\n        ELIJA LAS ESPECIFICACIONES");
                    while ((linea = br3.readLine()) != null) {
                        System.out.println(linea);
                    }
                    linea = "";
                    System.out.println("escriba la especificacion a seleccionar: ");
                    valorEspecificacion = scan.next();

                    String otroValor = "";
                    String nuevoValor = "";
                    while (!otroValor.equals("no")) {
                        System.out.println("Ingrese el valor para la especificacion seleccionada: ");
                        nuevoValor = scan.next();
                        if (valoresEspecificaciones.equals("")) {
                            valoresEspecificaciones = nuevoValor;
                        } else {
                            valoresEspecificaciones = valoresEspecificaciones + "," + nuevoValor;
                        }
                        System.out.println("Agregar otro valor? si/no");
                        otroValor = scan.next();
                    }
                    especificaciones = especificaciones + valorEspecificacion + ":" + valoresEspecificaciones + ";";
                    valoresEspecificaciones = "";

                    System.out.println("Seleccionar otra especificacion? si/no");
                    aniadirOtraEspecificacion = scan.next();

                }

                FileReader frProductos = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
                BufferedReader brProductos = new BufferedReader(frProductos);

                String leerLinea = "";
                while ((leerLinea = brProductos.readLine()) != null) {
                    String[] datos = leerLinea.split("\\|");
                    if (datos[1].compareTo(nombreProducto) == 0) {
                        existe = "yaExiste";
                        break;
                    }
                }

                String aniadirDescripcion = "";
                String descripcionProducto = "";
                String aniadirPrecio = "";
                float precioProducto = 0.0f;
                String aniadirCantStock = "";
                int cantStock = 0;

                if (existe.equals("yaExiste")) {
                    System.out.println("El nombre ya se encuentra registrado");
                } else {

                    System.out.println("Ingresar descripcion? si/no");
                    aniadirDescripcion = scan.next();

                    if (aniadirDescripcion.equals("si")) {
                        System.out.println("Ingrese la descripcion: ");
                        descripcionProducto = scan.next();
                    }

                    System.out.println("Ingresar precio de venta? si/no");
                    aniadirPrecio = scan.next();
                    if (aniadirPrecio.equals("si")) {
                        System.out.println("Ingrese el precio: ");
                        precioProducto = scan.nextFloat();
                    }

                    System.out.println("Ingresar cantidad inicial? si/no");
                    aniadirCantStock = scan.next();
                    if (aniadirCantStock.equals("si")) {
                        System.out.println("Ingrese la cantidad inicial: ");
                        cantStock = scan.nextInt();
                    }

                    operaciones.asignarCodigo(nombreArchivo, nombreProducto, categoria, caracteristicas, especificaciones, descripcionProducto, precioProducto, cantStock);
                }

                brProductos.close();
                br3.close();
                br2.close();
                br.close();
                fr.close();
                fr2.close();
                fr3.close();
                frProductos.close();

            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void asignarCodigo(String nombreArchivo, String nombreProducto, String categoria, String caracteristicas, String especificaciones, String descripcionProducto, float precioProducto, int cantStock) {
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String fechaParaCodigo = sdf.format(fecha);

        String codigo = fechaParaCodigo;

        try {
            FileWriter fw = new FileWriter(f, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(codigo + "|" + nombreProducto + "|" + categoria + "|" + "caracteristicas(" + caracteristicas + ")" + "|" + "especificaciones(" + especificaciones + ")" + "|" + descripcionProducto + "|" + precioProducto + "|" + cantStock + "\n");
                bw.close();
                System.out.println("Registro exitoso");

            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerArchivos(String nombreArchivo, String nombre, String contrasenia) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);

        if (nombreArchivo.equals("usuarios.txt")) {
            try {
                FileReader fr = new FileReader(f);
                try (BufferedReader br = new BufferedReader(fr)) {

                    String linea = "";

                    while ((linea = br.readLine()) != null) {
                        String[] datos = linea.split("\\|");
                        if (datos[0].equals(nombre) && datos[1].equals(contrasenia)) {
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
        } else if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt") || nombreArchivo.equals("productos.txt")) {

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

    public void historial(String codigoProducto) {
        File fEntradas = new File("src\\sistema\\de\\gestion\\de\\inventarios\\entradas.txt");
        File fSalidas = new File("src\\sistema\\de\\gestion\\de\\inventarios\\salidas.txt");

        try {
            FileReader frEntradas = new FileReader(fEntradas);
            BufferedReader brEntradas = new BufferedReader(frEntradas);

            String linea = "";
            System.out.println("LAS ENTRADAS DEL PRODUCTO REGISTRADAS SON: ");
            while ((linea = brEntradas.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].equals(codigoProducto)) {
                    System.out.println(linea);
                }
            }

            FileReader frSalidas = new FileReader(fSalidas);
            BufferedReader brSalidas = new BufferedReader(frSalidas);
            linea = "";
            System.out.println("LAS SALIDAS DEL PRODUCTO REGISTRADAS SON: ");
            while ((linea = brSalidas.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].equals(codigoProducto)) {
                    System.out.println(linea);
                }
            }

            frEntradas.close();
            brEntradas.close();
            frSalidas.close();
            brSalidas.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
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

                    if (nombreArchivo.equals("especificaciones.txt")) {
                        System.out.println("Ingrese el tipo de dato: ");
                        tipoDato = scan.next();
                    }
                    if (nombreArchivo.equals("categorias.txt")) {
                        String aniadir = "si";
                        do {
                            System.out.println("añadir subcategoría? si/no");
                            aniadir = scan.next();
                            if (!aniadir.equals("si")) {
                                continue;
                            }
                            System.out.println("ingrese el nombre de la subcategoria: ");
                            String subcategoria = scan.next();
                            nombreCategoria = nombreCategoria + ">" + subcategoria;
                        } while (aniadir.equals("si"));
                    }
                    System.out.println("Quiere ingresar una descripcion? si/no");
                    String aniadirDescripcion = scan.next();

                    if (aniadirDescripcion.equals("si")) {
                        System.out.println("Descripción: ");
                        descCategoria = scan.next();
                    }
                    operaciones.llenarArchivos(nombreArchivo, nombreCategoria, descCategoria, tipoDato);
                    nombreCategoria = "";
                    break;
                case 2:
                    while (nombreCategoria.equals("")) {
                        System.out.println("Ingrese: \n");
                        System.out.println("Número del registro a modificar: ");
                        nombreCategoria = scan.next();
                        System.out.println("Nuevo nombre: ");
                        nuevoNombre = scan.next();
                    }
                    if (nombreArchivo.equals("especificaciones.txt")) {
                        System.out.println("Ingrese el tipo de dato: ");
                        tipoDato = scan.next();
                    }
                    if (nombreArchivo.equals("categorias.txt")) {
                        String aniadir = "si";
                        do {
                            System.out.println("añadir subcategoría? si/no");
                            aniadir = scan.next();
                            if (!aniadir.equals("si")) {
                                continue;
                            }
                            System.out.println("ingrese el nombre de la subcategoria: ");
                            String subcategoria = scan.next();
                            nuevoNombre = nuevoNombre + ">" + subcategoria;
                        } while (aniadir.equals("si"));

                        System.out.println("Cambiar descripcion? si/no");
                        String cambiarDescripcion = scan.next();

                        if (cambiarDescripcion.equals("si")) {
                            System.out.println("Descripción: ");
                            descCategoria = scan.next();
                        }
                    } else {

                        System.out.println("Cambiar descripcion? si/no");
                        String cambiarDescripcion = scan.next();

                        if (cambiarDescripcion.equals("si")) {
                            System.out.println("Descripción: ");
                            descCategoria = scan.next();
                        }
                    }

                    System.out.println(nuevoNombre);
                    operaciones.modificarArchivos(nombreArchivo, nombreCategoria, nuevoNombre, descCategoria, tipoDato);
                    nombreCategoria = "";
                    nuevoNombre = "";
                    break;
                case 3:
                    while (nombreCategoria.equals("")) {
                        System.out.println("Ingrese el número de el registro a eliminar");
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
                System.out.println("4. Alta de producto");
                System.out.println("5. Modificar producto");
                System.out.println("6. Baja de producto");
                System.out.println("7. Salir");
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
                        nombreArchivo = "productos.txt";
                        System.out.println("            GESTION DE PRODUCTOS ");
                        System.out.println("\n estos son los productos existentes: ");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        operaciones.llenarArchivos(nombreArchivo, "", "", "");
                        break;
                    case 5:
                        nombreArchivo = "productos.txt";
                        System.out.println("Estos son los productos existentes: \n");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        System.out.println("\n Ingrese el codigo del producto a modificar: ");
                        String codigo = scan.next();
                        System.out.println("\n Ingrese el nuevo nombre: ");
                        String nuevoNombre = scan.next();
                        String linea = "";
                        String categoria = "";
                        String caracteristicas = "";
                        String especificaciones = "";
                        String aniadirDescripcion = "";
                        String descripcionProducto = "";
                        String aniadirPrecio = "";
                        float precioProducto = 0.0f;
                        String aniadirCantStock = "";
                        int cantStock = 0;
                        String existe = "";
                        
                        try {
                            FileReader fr = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\categorias.txt");
                            BufferedReader br = new BufferedReader(fr);

                            System.out.println("\n        ELIJA LA CATEGORIA");
                            while ((linea = br.readLine()) != null) {
                                System.out.println(linea);
                            }

                            do {
                                System.out.println("Seleccionar la categoria: ");
                                categoria = scan.next();

                                String leerLineas = "";

                                while ((leerLineas = br.readLine()) != null) {
                                    String[] datos = leerLineas.split("\\|");
                                    if (datos[0].compareTo(categoria) == 0) {
                                        existe = "siExiste";
                                    }
                                }
                            }while(!existe.equals("siExiste")); 
                            
                            FileReader fr2 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\caracteristicas.txt");
                            BufferedReader br2 = new BufferedReader(fr2);

                            String aniadirOtraCaracteristica = "";
                            while (!aniadirOtraCaracteristica.equals("no")) {
                                System.out.println("\n        ELIJA LAS CARACTERISTICAS");
                                while ((linea = br2.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                linea = "";
                                System.out.println("escriba la caracteristica a seleccionar: ");
                                String valorCaracteristica = scan.next();

                                String otroValor = "";
                                String nuevoValor = "";
                                String valoresCaracteristicas = "";
                                while (!otroValor.equals("no")) {
                                    System.out.println("Ingrese el valor para la característica seleccionada: ");
                                    nuevoValor = scan.next();
                                    if (valoresCaracteristicas.equals("")) {
                                        valoresCaracteristicas = nuevoValor;
                                    } else {
                                        valoresCaracteristicas = valoresCaracteristicas + "," + nuevoValor;
                                    }
                                    System.out.println("Agregar otro valor? si/no");
                                    otroValor = scan.next();
                                }

                                caracteristicas = caracteristicas + valorCaracteristica + ":" + valoresCaracteristicas + ";";
                                valoresCaracteristicas = "";

                                System.out.println("Seleccionar otra caracteristica? si/no");
                                aniadirOtraCaracteristica = scan.next();

                            }
                            FileReader fr3 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\especificaciones.txt");
                            BufferedReader br3 = new BufferedReader(fr3);

                            String aniadirOtraEspecificacion = "";
                            while (!aniadirOtraEspecificacion.equals("no")) {
                                System.out.println("\n        ELIJA LAS ESPECIFICACIONES");
                                while ((linea = br3.readLine()) != null) {
                                    System.out.println(linea);
                                }
                                linea = "";
                                System.out.println("escriba la especificacion a seleccionar: ");
                                String valorEspecificacion = scan.next();

                                String otroValor = "";
                                String nuevoValor = "";
                                String valoresEspecificaciones = "";
                                while (!otroValor.equals("no")) {
                                    System.out.println("Ingrese el valor para la especificacion seleccionada: ");
                                    nuevoValor = scan.next();
                                    if (valoresEspecificaciones.equals("")) {
                                        valoresEspecificaciones = nuevoValor;
                                    } else {
                                        valoresEspecificaciones = valoresEspecificaciones + "," + nuevoValor;
                                    }
                                    System.out.println("Agregar otro valor? si/no");
                                    otroValor = scan.next();
                                }

                                especificaciones = especificaciones + valorEspecificacion + ":" + valoresEspecificaciones + ";";
                                valoresEspecificaciones = "";

                                System.out.println("Seleccionar otra especificacion? si/no");
                                aniadirOtraEspecificacion = scan.next();
                            }

                            System.out.println("Ingresar descripcion? si/no");
                            aniadirDescripcion = scan.next();

                            if (aniadirDescripcion.equals("si")) {
                                System.out.println("Ingrese la descripcion: ");
                                descripcionProducto = scan.next();
                            }

                            System.out.println("Ingresar precio de venta? si/no");
                            aniadirPrecio = scan.next();
                            if (aniadirPrecio.equals("si")) {
                                System.out.println("Ingrese el precio: ");
                                precioProducto = scan.nextFloat();
                            }

                            System.out.println("Ingresar cantidad inicial? si/no");
                            aniadirCantStock = scan.next();
                            if (aniadirCantStock.equals("si")) {
                                System.out.println("Ingrese la cantidad inicial: ");
                                cantStock = scan.nextInt();
                            }

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String nuevoRegistro = (nuevoNombre + "|" + categoria + "|" + "caracteristicas(" + caracteristicas + ")" + "|" + "especificaciones(" + especificaciones + ")" + "|" + descripcionProducto + "|" + precioProducto + "|" + cantStock);

                        operaciones.modificarArchivos(nombreArchivo, codigo, nuevoRegistro, "", "");
                        break;

                    case 6:
                        nombreArchivo = "productos.txt";
                        System.out.println("Estos son los productos existentes: \n");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        System.out.println("\n Ingrese el codigo del producto a eliminar: ");
                        String nombreProd = scan.next();
                        operaciones.eliminarArchivos(nombreArchivo, nombreProd);
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }

                break;
            case 2:
                System.out.println("\n Qué desea realizar: ");
                System.out.println("1. Entrada de productos");
                System.out.println("2. Salida de productos");
                System.out.println("3. Ver historial");
                System.out.println("4. Salir");
                eleccionMenuSecundario = scan.nextInt();

                switch (eleccionMenuSecundario) {
                    case 1:
                        nombreArchivo = "entradas.txt";
                        System.out.println("\n    ESTOS SON LOS PRODUCTOS EXISTENTES");
                        operaciones.leerArchivos("productos.txt", "", "");
                        System.out.println("\n Ingrese el codigo del producto: ");
                        String codigo = scan.next();
                        operaciones.buscarArchivos(nombreArchivo, codigo);
                        break;
                    case 2:
                        nombreArchivo = "salidas.txt";
                        System.out.println("\n    ESTOS SON LOS PRODUCTOS EXISTENTES");
                        operaciones.leerArchivos("productos.txt", "", "");
                        System.out.println("\n Ingrese el codigo del producto: ");
                        String codigoProd = scan.next();
                        operaciones.buscarArchivos(nombreArchivo, codigoProd);
                        break;
                    case 3:
                        nombreArchivo = "productos.txt";
                        System.out.println("\n      ESTOS SON LOS PRODUCTOS EXISTENTES");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        System.out.println("\n      Ingrese el codigo del producto para ver su historial: ");
                        String cod = scan.next();
                        operaciones.historial(cod);
                        break;
                    case 4:
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
