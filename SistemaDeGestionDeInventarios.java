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
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
                        if (nombreArchivo.equals("productos.txt")) {
                            linea = (datos[0] + "|" + nuevoNombre);
                        } else if (nombreArchivo.equals("pedidos.txt") && descCategoria.equals("cambiarEstado")) {
                            linea = (datos[0] + "|" + datos[1] + "|" + datos[2] + "|" + datos[3] + "|" + nuevoNombre + "|" + datos[5] + "|" + datos[6]);
                        } else if (!descCategoria.equals("")) {
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

    public void buscarArchivos(String nombreArchivo, String nombre, int cant, String usuario) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
        File fMovimientos = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);

        String linea = "";
        String existe = "";
        int total = 0;

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(fMovimientos, true);
            BufferedWriter bw = new BufferedWriter(fw);

            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String fechaParaCodigo = sdf.format(fecha);

            String cantidad = "";

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].compareTo(nombre) == 0) {
                    System.out.println("Se encontro el archivo: " + linea);
                    existe = "siExiste";

                    if (nombreArchivo.equals("entradas.txt") || nombreArchivo.equals("salidas.txt")) {
                        cantidad = String.valueOf(cant);
                        System.out.println("operacion exitosa");
                        existe = "siExiste";
                    }
                    bw.write(nombre + "|" + cantidad + "|" + fechaParaCodigo + "|" + usuario + "\n");
                    break;
                }
            }

            if (!existe.equals("siExiste")) {
                System.out.println("El producto no esta registrado");
            }

            bw.close();
            fw.close();
            fr.close();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarStock(String nombre, String operacion, int cant, String usuario) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        File existencias = new File("src\\sistema\\de\\gestion\\de\\inventarios\\existencias.txt");
        try {
            FileReader fr = new FileReader(existencias);
            BufferedReader br = new BufferedReader(fr);

            File fc = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
            FileWriter fw = new FileWriter(fc);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea = "";
            String cantidad = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].compareTo(nombre) == 0) {
                    cantidad = datos[1];
                    if (operacion.equals("entradas.txt")) {
                        operaciones.buscarArchivos(operacion, nombre, cant, usuario);
                        cant = cant + parseInt(cantidad);
                    } else {

                        if (cant > parseInt(cantidad)) {
                            System.out.println("La cantidad es mayora a la que se encuentra en stock, no es posible retirar ");
                            cant = parseInt(cantidad);
                        } else {
                            operaciones.buscarArchivos(operacion, nombre, cant, usuario);
                            cant = parseInt(cantidad) - cant;
                        }
                    }
                    bw.write(datos[0] + "|" + (String.valueOf(cant)) + "\n");
                } else {
                    bw.write(linea + "\n");
                }
            }

            fr.close();
            br.close();
            bw.close();

            Files.move(fc.toPath(), existencias.toPath(), REPLACE_EXISTING);
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
        int numeracion = 0;
        String numero = "";

        if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt")) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String leerLineas = "";

                while ((leerLineas = br.readLine()) != null) {
                    String[] datos = leerLineas.split("\\|");
                    if (datos[1].compareTo(nombre) == 0) {
                        existe = "yaExiste";
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
                        } else if (nombreArchivo.equals("pedidos.txt")) {
                            bw.write(numeracion + "|" + nombre + "\n");
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
                existe = "";
                String proveedor = "";

                System.out.println("Ingrese el nombre del producto: ");
                nombreProducto = scan.next();

                FileReader fr = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\categorias.txt");
                BufferedReader br = new BufferedReader(fr);

                System.out.println("\n        ELIJA LA CATEGORIA");
                operaciones.leerArchivos("categorias.txt", "", "");

                System.out.println("Seleccionar la categoria: ");
                categoria = scan.next();

                String leerLineas = "";
                while ((leerLineas = br.readLine()) != null) {
                    String[] datos = leerLineas.split("\\|");
                    if (datos[0].equals(categoria)) {
                        existe = "siExiste";
                        break;
                    }
                }

                if (existe.equals("siExiste")) {
                    System.out.println("Dato guardado");
                } else {
                    System.out.println("dato no valido, para añadir una categoria valida modifique el producto al terminar de crearlo :c");
                    categoria = "";
                }

                FileReader fr2 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\caracteristicas.txt");
                BufferedReader br2 = new BufferedReader(fr2);

                String aniadirOtraCaracteristica = "";
                while (!aniadirOtraCaracteristica.equals("no")) {
                    System.out.println("\n        ELIJA LAS CARACTERISTICAS");
                    operaciones.leerArchivos("caracteristicas.txt", "", "");
                    linea = "";
                    System.out.println("escriba la caracteristica a seleccionar: ");
                    valorCaracteristica = scan.next();

                    existe = "";
                    leerLineas = "";
                    while ((leerLineas = br2.readLine()) != null) {
                        String[] datos = leerLineas.split("\\|");
                        if (datos[0].equals(valorCaracteristica)) {
                            existe = "siExiste";
                            break;
                        }
                    }

                    if (existe.equals("siExiste")) {
                        System.out.println("Dato valido \n");
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
                    } else {
                        System.out.println("dato no valido, para añadir una caracteristica valida modifique el producto al terminar de crearlo :c");
                        valorCaracteristica = "";
                        aniadirOtraCaracteristica = "no";
                    }

                }

                FileReader fr3 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\especificaciones.txt");
                BufferedReader br3 = new BufferedReader(fr3);

                String aniadirOtraEspecificacion = "";
                while (!aniadirOtraEspecificacion.equals("no")) {
                    System.out.println("\n        ELIJA LAS ESPECIFICACIONES");
                    operaciones.leerArchivos("especificaciones.txt", "", "");
                    linea = "";
                    System.out.println("escriba la especificacion a seleccionar: ");
                    valorEspecificacion = scan.next();

                    existe = "";
                    leerLineas = "";
                    while ((leerLineas = br3.readLine()) != null) {
                        String[] datos = leerLineas.split("\\|");
                        if (datos[0].equals(valorEspecificacion)) {
                            existe = "siExiste";
                            break;
                        }
                    }

                    if (existe.equals("siExiste")) {
                        System.out.println("Dato valido \n");
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
                    } else {
                        System.out.println("dato no valido, para añadir una caracteristica valida modifique el producto al terminar de crearlo :c");
                        valorCaracteristica = "";
                        aniadirOtraEspecificacion = "no";
                    }

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
                        do {
                            System.out.println("Ingrese el precio: ");
                            precioProducto = scan.nextFloat();
                        } while (precioProducto <= 0);

                    }

                    System.out.println("Ingresar cantidad inicial? si/no");
                    aniadirCantStock = scan.next();
                    if (aniadirCantStock.equals("si")) {
                        do {
                            System.out.println("Ingrese la cantidad inicial: ");
                            cantStock = scan.nextInt();
                        } while (cantStock <= 0);
                    }

                    FileReader frProveedor = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\proveedores.txt");
                    BufferedReader brProveedor = new BufferedReader(frProveedor);

                    System.out.println("\n        ELIJA EL PROVEEDOR");
                    operaciones.leerArchivos("proveedores.txt", "", "");

                    System.out.println("Seleccionar proveedor: ");
                    proveedor = scan.next();

                    leerLineas = "";
                    existe = "";
                    while ((leerLineas = brProveedor.readLine()) != null) {
                        String[] datos = leerLineas.split("\\|");
                        if (datos[0].equals(proveedor)) {
                            existe = "siExiste";
                            proveedor = datos[0];
                            break;
                        }
                    }

                    if (existe.equals("siExiste")) {
                        System.out.println("Dato guardado");
                    } else {
                        System.out.println("dato no valido, para añadir un proveedor valido modifique el producto al terminar de crearlo :c");
                        proveedor = "";
                    }

                    operaciones.asignarCodigo(nombreArchivo, nombreProducto, categoria, caracteristicas, especificaciones, descripcionProducto, precioProducto, cantStock, proveedor);
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

        } else if (nombreArchivo.equals("pedidos.txt")) {
            numeracion = 0;
            String num = "";
            try {
                FileReader frPedidos = new FileReader(f);
                BufferedReader brPedidos = new BufferedReader(frPedidos);

                String leerLineas = "";
                existe = "";

                while ((leerLineas = brPedidos.readLine()) != null) {
                    String[] datosPedidos = leerLineas.split("\\|");
                    num = datosPedidos[0];
                }
                numeracion = parseInt(num) + 1;

                FileWriter fw = new FileWriter(f, true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(numeracion + "|" + nombre + "\n");
                    System.out.println("Registro exitoso");
                }

                frPedidos.close();
                brPedidos.close();

            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void asignarCodigo(String nombreArchivo, String nombreProducto, String categoria, String caracteristicas, String especificaciones, String descripcionProducto, float precioProducto, int cantStock, String proveedor) {
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
        File existencias = new File("src\\sistema\\de\\gestion\\de\\inventarios\\existencias.txt");

        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String fechaParaCodigo = sdf.format(fecha);

        String codigo = fechaParaCodigo;

        try {
            FileWriter fw = new FileWriter(f, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(codigo + "|" + nombreProducto + "|" + categoria + "|" + "caracteristicas(" + caracteristicas + ")" + "|" + "especificaciones(" + especificaciones + ")" + "|" + descripcionProducto + "|" + precioProducto + "|" + cantStock + "|" + proveedor + "\n");
                bw.close();
                System.out.println("Registro exitoso");

                FileWriter fExistencias = new FileWriter(existencias, true);
                BufferedWriter bwExistencias = new BufferedWriter(fExistencias);
                bwExistencias.write(codigo + "|" + cantStock);
                bwExistencias.close();
                fExistencias.close();
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
                                operaciones.menuEncargado(nombre);
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
        } else if (nombreArchivo.equals("categorias.txt") || nombreArchivo.equals("caracteristicas.txt") || nombreArchivo.equals("especificaciones.txt") || nombreArchivo.equals("productos.txt") || nombreArchivo.equals("proveedores.txt") || nombreArchivo.equals("pedidos.txt")) {

            try {
                FileReader fr2 = new FileReader(f);
                BufferedReader br = new BufferedReader(fr2);

                String leerLinea = "";

                while ((leerLinea = br.readLine()) != null) {
                    if (nombreArchivo.equals("productos.txt") && contrasenia.equals("buscarSegunProveedor")) {
                        String datos[] = leerLinea.split("\\|");
                        if (datos[8].equals(nombre)) {
                            System.out.println(leerLinea);
                        }
                    } else if (nombreArchivo.equals("pedidos.txt") && contrasenia.equals("buscarPorCodigo")) {
                        String datos[] = leerLinea.split("\\|");
                        if (datos[0].equals(nombre)) {
                            System.out.println("proveedor: " + datos[1]);
                            System.out.println("fecha de creacion: " + datos[2]);
                            System.out.println("fecha de entrega estimada: " + datos[3]);
                            System.out.println("estado del pedido: " + datos[4]);
                            System.out.println("productos solicitados: " + datos[5]);
                            System.out.println("total del pedido: " + datos[6]);
                        }
                    } else {
                        System.out.println(leerLinea);
                    }
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

    public void llenarRecepcion(String nombreArchivo, String nombre) {

        File recepcion = new File("src\\sistema\\de\\gestion\\de\\inventarios\\recepcion.txt");

        try {
            FileReader frRecepcion = new FileReader(recepcion);
            BufferedReader brRecepcion = new BufferedReader(frRecepcion);

            int numeracion = 0;
            String num = "";
            String leerLineas = "";
            while ((leerLineas = brRecepcion.readLine()) != null) {
                String[] datosPedidos = leerLineas.split("\\|");
                num = datosPedidos[0];
            }
            numeracion = parseInt(num) + 1;

            FileWriter fwRecepcion = new FileWriter(recepcion, true);
            try (BufferedWriter bwRecepcion = new BufferedWriter(fwRecepcion)) {
                bwRecepcion.write(numeracion + "|" + nombre + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void historial(String codigoProducto, String fecha) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        File fEntradas = new File("src\\sistema\\de\\gestion\\de\\inventarios\\entradas.txt");
        File fSalidas = new File("src\\sistema\\de\\gestion\\de\\inventarios\\salidas.txt");

        try {
            FileReader frEntradas = new FileReader(fEntradas);
            BufferedReader brEntradas = new BufferedReader(frEntradas);

            String linea = "";
            System.out.println("\n            HISTORIAL DEL PRODUCTO " + codigoProducto);
            while ((linea = brEntradas.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].equals(codigoProducto)) {
                    if (datos[0].equals(codigoProducto) && datos[2].contains(fecha)) {
                        System.out.println("entrada realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos registrados por: " + datos[3] + "\n");
                    } else {
                        System.out.println("entrada realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos registrados por: " + datos[3] + "\n");
                    }
                }
            }

            FileReader frSalidas = new FileReader(fSalidas);
            BufferedReader brSalidas = new BufferedReader(frSalidas);
            linea = "";
            while ((linea = brSalidas.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos[0].equals(codigoProducto)) {
                    if (datos[2].contains(fecha) && datos[2].contains(fecha)) {
                        System.out.println("salida realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos retirados por: " + datos[3] + "\n");
                    } else {
                        System.out.println("salida realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos retirados por: " + datos[3] + "\n");
                    }
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
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        int eleccionMenuPrincipal = 0;
        int eleccionMenuSecundario = 0;
        String filtrado = "no";
        String orden = "";
        String exportar = "";

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

                    if (eleccionMenuSecundario == 1) {
                        System.out.println("Estos son todos los productos en existencia: ");
                        operaciones.leerArchivos("productos.txt", "", "");
                        System.out.println("Filtrar por categoria? si/no");
                        String filtrar = scan.next();

                        if (filtrar.equals("si")) {
                            System.out.println("Ingrese el filtro para generar el informe: categoria/");
                            filtrado = scan.next();
                            System.out.println("Ingresar el criterio para ordenar: nombre producto/cant stock");
                            orden = scan.next();
                            System.out.println("Exportar el informe? si/no(si solo quiere ver el informe)");
                            exportar = scan.next();
                            operaciones.informeInventario("productos.txt", filtrado, orden, exportar);
                        } else {
                            System.out.println("Exportar el informe? si/no");
                            exportar = scan.next();
                            operaciones.informeInventario("productos.txt", filtrado, orden, exportar);
                        }
                    } else if (eleccionMenuSecundario == 2) {
                        System.out.println("\n Estos son todos los productos en existencia: ");
                        operaciones.leerArchivos("productos.txt", "", "");
                        System.out.println("\n Ingrese el codigo del producto: ");
                        String codProducto = scan.next();
                        System.out.println("\n Ingrese la fecha para filtrar: ");
                        String fecha = scan.next();
                        operaciones.historial(codProducto, fecha);

                        System.out.println("\n exportar? si/no");
                        String export = scan.next();

                        if (export.equals("si")) {
                            operaciones.informeMovimientos(codProducto, fecha);
                            operaciones.informeMovimientos2(codProducto, fecha);
                            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
                            f.delete();
                        }else{
                            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
                            f.delete();
                        }
                    }

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

    public void informeMovimientos(String codigo, String fecha) {
        File informe = new File("src\\sistema\\de\\gestion\\de\\inventarios\\informeMovimientos.csv");
        try {
            FileWriter fw = new FileWriter(informe);
            BufferedWriter bw = new BufferedWriter(fw);

            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\entradas.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String leer = "";

            while ((leer = br.readLine()) != null) {
                String[] datos = leer.split("\\|");
                if (datos[0].equals(codigo) && datos[2].contains(fecha)) {
                    bw.write("entrada realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos registrados por: " + datos[3] + "\n" );
                }
            }

            bw.close();
            fr.close();
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void informeMovimientos2(String codigo, String fecha) {
        File informe = new File("src\\sistema\\de\\gestion\\de\\inventarios\\informeMovimientos.csv");
        try {
            FileWriter fw = new FileWriter(informe, true);
            BufferedWriter bw = new BufferedWriter(fw);

            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\salidas.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String leer = "";

            while ((leer = br.readLine()) != null) {
                String[] datos = leer.split("\\|");
                if (datos[0].equals(codigo) && datos[2].contains(fecha)) {
                    bw.write("salida realizada el: " + datos[2] + " ,con un total de: " + datos[1] + " productos retirados por: " + datos[3] + "\n");
                }
            }

            bw.close();
            fr.close();
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void informeInventario(String nombreArchivo, String filtrado, String orden, String exportar) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        try {
            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea = "";

            System.out.println("\n              INFORME FINAL: ");

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                if (!filtrado.equals("no")) {
                    if (datos[2].compareTo(filtrado) == 0) {
                        if (datos[1].contains(orden) || datos[7].contains(orden)) {
                            linea = datos[1] + "|" + datos[2];
                            operaciones.completarInforme(linea, datos[0], datos[6]);
                        }
                    }
                } else {
                    operaciones.completarInforme(linea, "", "");
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exportar.equals("si")) {
            operaciones.convertirCSV();
        }

        File informe = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
        informe.delete();

    }

    public void convertirCSV() {
        File informe = new File("src\\sistema\\de\\gestion\\de\\inventarios\\informe.csv");
        try {
            FileWriter fw = new FileWriter(informe);
            BufferedWriter bw = new BufferedWriter(fw);

            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String leer = "";

            while ((leer = br.readLine()) != null) {
                bw.write(leer + "\n");
            }

            bw.close();
            fr.close();
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void completarInforme(String linea, String codigo, String precio) {

        File fc = new File("src\\sistema\\de\\gestion\\de\\inventarios\\archivo_texto_copia.txt");
        String cantidad = "";
        float precioProd = 0.0f;
        float valorTotal = 0.0f;
        try {
            File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\existencias.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String leer = "";

            while ((leer = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String[] datosExistencias = leer.split("\\|");
                if (datosExistencias[0].equals(codigo)) {
                    cantidad = datosExistencias[1];
                    System.out.println(cantidad);
                    break;
                } else {
                }

            }

            br.close();

            FileWriter fw = new FileWriter(fc, true);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println(linea);

            if (codigo.equals("") && precio.equals("")) {
                bw.write(linea + "\n");
                bw.close();
            } else {

                precioProd = Float.valueOf(precio);
                valorTotal = precioProd * Float.valueOf(cantidad);
                bw.write(linea + "|" + cantidad + "|" + valorTotal + "|" + "30" + "\n");
                bw.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
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

    public void menuEncargado(String usuario) {
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
                        String valoresCaracteristicas = "";
                        String valorCaracteristica = "";
                        String valoresEspecificaciones = "";
                        String valorEspecificacion = "";
                        String especificaciones = "";
                        String aniadirDescripcion = "";
                        String descripcionProducto = "";
                        String aniadirPrecio = "";
                        float precioProducto = 0.0f;
                        String aniadirCantStock = "";
                        int cantStock = 0;
                        String existe = "";
                        String proveedor = "";

                        try {

                            FileReader fr = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\categorias.txt");
                            BufferedReader br = new BufferedReader(fr);

                            System.out.println("\n        ELIJA LA NUEVA CATEGORIA");
                            operaciones.leerArchivos("categorias.txt", "", "");

                            System.out.println("Seleccionar la categoria: ");
                            categoria = scan.next();

                            String leerLineas = "";
                            while ((leerLineas = br.readLine()) != null) {
                                String[] datos = leerLineas.split("\\|");
                                if (datos[0].equals(categoria)) {
                                    existe = "siExiste";
                                    break;
                                }
                            }
                            if (existe.equals("siExiste")) {
                                System.out.println("Dato guardado");
                            } else {
                                System.out.println("dato no valido, para añadir una categoria valida modifique el producto al terminar de crearlo :c");
                                categoria = "";
                            }

                            FileReader fr2 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\caracteristicas.txt");
                            BufferedReader br2 = new BufferedReader(fr2);

                            String aniadirOtraCaracteristica = "";
                            while (!aniadirOtraCaracteristica.equals("no")) {
                                System.out.println("\n        ELIJA LAS CARACTERISTICAS");
                                operaciones.leerArchivos("caracteristicas.txt", "", "");
                                linea = "";
                                System.out.println("escriba la caracteristica a seleccionar: ");
                                valorCaracteristica = scan.next();

                                existe = "";
                                leerLineas = "";
                                while ((leerLineas = br2.readLine()) != null) {
                                    String[] datos = leerLineas.split("\\|");
                                    if (datos[0].equals(valorCaracteristica)) {
                                        existe = "siExiste";
                                        break;
                                    }
                                }

                                if (existe.equals("siExiste")) {
                                    System.out.println("Dato valido \n");
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
                                } else {
                                    System.out.println("dato no valido, para añadir una caracteristica valida modifique el producto al terminar de crearlo :c");
                                    valorCaracteristica = "";
                                    aniadirOtraCaracteristica = "no";
                                }

                            }

                            FileReader fr3 = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\especificaciones.txt");
                            BufferedReader br3 = new BufferedReader(fr3);

                            String aniadirOtraEspecificacion = "";
                            while (!aniadirOtraEspecificacion.equals("no")) {
                                System.out.println("\n        ELIJA LAS ESPECIFICACIONES");
                                operaciones.leerArchivos("especificaciones.txt", "", "");
                                linea = "";
                                System.out.println("escriba la especificacion a seleccionar: ");
                                valorEspecificacion = scan.next();

                                existe = "";
                                leerLineas = "";
                                while ((leerLineas = br3.readLine()) != null) {
                                    String[] datos = leerLineas.split("\\|");
                                    if (datos[0].equals(valorEspecificacion)) {
                                        existe = "siExiste";
                                        break;
                                    }
                                }

                                if (existe.equals("siExiste")) {
                                    System.out.println("Dato valido \n");
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
                                } else {
                                    System.out.println("dato no valido, para añadir una caracteristica valida modifique el producto al terminar de crearlo :c");
                                    valorCaracteristica = "";
                                    aniadirOtraEspecificacion = "no";
                                }

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
                                do {
                                    System.out.println("Ingrese el precio: ");
                                    precioProducto = scan.nextFloat();
                                } while (precioProducto <= 0);

                            }

                            System.out.println("Ingresar cantidad inicial? si/no");
                            aniadirCantStock = scan.next();
                            if (aniadirCantStock.equals("si")) {
                                do {
                                    System.out.println("Ingrese la cantidad inicial: ");
                                    cantStock = scan.nextInt();
                                } while (cantStock <= 0);
                            }

                            System.out.println("Ingrese el nombre del proveedor: ");
                            proveedor = scan.next();

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        String nuevoRegistro = (nuevoNombre + "|" + categoria + "|" + "caracteristicas(" + caracteristicas + ")" + "|" + "especificaciones(" + especificaciones + ")" + "|" + descripcionProducto + "|" + precioProducto + "|" + cantStock + "|" + proveedor);

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
                int cant = 0;

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
                        System.out.println("Cantidad del producto que desea ingresar: ");
                        cant = scan.nextInt();
                        operaciones.actualizarStock(codigo, nombreArchivo, cant, usuario);
                        break;
                    case 2:
                        nombreArchivo = "salidas.txt";
                        System.out.println("\n    ESTOS SON LOS PRODUCTOS EXISTENTES");
                        operaciones.leerArchivos("productos.txt", "", "");
                        System.out.println("\n Ingrese el codigo del producto: ");
                        String codigoProd = scan.next();
                        System.out.println("Cantidad del producto que desea vender: ");
                        cant = scan.nextInt();
                        operaciones.actualizarStock(codigoProd, nombreArchivo, cant, usuario);
                        break;
                    case 3:
                        nombreArchivo = "productos.txt";
                        System.out.println("\n      ESTOS SON LOS PRODUCTOS EXISTENTES");
                        operaciones.leerArchivos(nombreArchivo, "", "");
                        System.out.println("\n      Ingrese el codigo del producto para ver su historial: ");
                        String cod = scan.next();
                        operaciones.historial(cod, "");
                        break;
                    case 4:
                        break;
                }
                break;
        }
    }

    public void realizarPedido(String proveedor) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();
        String aniadirProducto = "";
        String producto = "";
        int cantidad = 0;
        String existe = "";
        String pedido = "";
        float total = 0.0f;
        float precio = 0.0f;

        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaPedido = sdf.format(fecha);

        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int mesFuturo = mes + 2;
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        String fechaEntrega = ("" + anio + "/" + mesFuturo + "/" + dia);

        System.out.println("\n Seleccione de los productos disponibles: ");
        operaciones.leerArchivos("productos.txt", proveedor, "buscarSegunProveedor");

        do {
            System.out.println("Ingrese el codigo del producto a pedir: ");
            producto = scan.next();

            try {
                FileReader fr = new FileReader("src\\sistema\\de\\gestion\\de\\inventarios\\productos.txt");
                BufferedReader br = new BufferedReader(fr);

                String leerLineas = "";
                existe = "";
                while ((leerLineas = br.readLine()) != null) {
                    String[] datos = leerLineas.split("\\|");
                    if (datos[0].equals(producto) && datos[8].equals(proveedor)) {
                        System.out.println("Cantidad a pedir: ");
                        cantidad = scan.nextInt();
                        precio = parseFloat(datos[6]) * cantidad;
                        existe = "siExiste";
                        break;
                    }
                }
                if (existe.equals("siExiste")) {
                    System.out.println("Dato valido");
                    pedido = pedido + producto + ":" + cantidad + ";";
                    total = total + precio;
                    System.out.println(total);
                } else {
                    System.out.println("dato no valido");
                }

                fr.close();
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Añadir otro producto al pedido? si/no: ");
            aniadirProducto = scan.next();
        } while (aniadirProducto.equals("si"));

        System.out.println(pedido);
        String realizarPedido = "";
        System.out.println("Seguro que quiere realizar el siguiente pedido: " + pedido + " ? si/no: ");
        realizarPedido = scan.next();

        if (realizarPedido.equals("si")) {
            operaciones.llenarArchivos("pedidos.txt", proveedor + "|" + fechaPedido + "|" + fechaEntrega + "|" + "pendiente" + "|" + pedido + "|" + total, "realizarPedido", "");
            operaciones.llenarRecepcion("recepcion.txt", fechaPedido + "|" + pedido);
        } else {
            System.out.println("pedido cancelado");
        }

    }

    public void filtrar(String nombreArchivo, String filtrado) {
        File f = new File("src\\sistema\\de\\gestion\\de\\inventarios\\" + nombreArchivo);

        String linea = "";

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while ((linea = br.readLine()) != null) {
                if (linea.contains(filtrado)) {
                    System.out.println(linea);
                } else {

                }
            }

            fr.close();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SistemaDeGestionDeInventarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recepcionPedidos(String nombreArchivo, String codigo) {
        String actualizar = "";
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

                if (datos[0].compareTo(codigo) == 0) {
                    System.out.println(datos[5]);
                    String[] subdatos = datos[5].split("\\;");
                    String[] cantidades = subdatos[0].split("\\:");
                    System.out.println(cantidades[1]);

                    System.out.println("Ingrese la cantidad recibida del producto: " + cantidades[0]);
                    String cantRecibida = scan.next();

                    if (parseInt(cantRecibida) < parseInt(cantidades[1])) {
                        System.out.println("Faltan productos");
                        subdatos[0] = cantidades[0] + ":" + cantRecibida;
                        datos[5] = subdatos[0];
                        actualizar = datos[0] + datos[2] + "|" + datos[5];

                    } else if (parseInt(cantRecibida) > parseInt(cantidades[1])) {
                        System.out.println("Hay un exceso de productos");
                    } else {
                        subdatos[0] = cantidades[0] + ":" + 0;
                        datos[5] = subdatos[0];
                        datos[4] = "completado";
                        actualizar = datos[0] + datos[2] + "|" + datos[5];
                    }
                    linea = datos[0] + "|" + datos[1] + "|" + datos[2] + "|" + datos[3] + "|" + datos[4] + "|" + datos[5] + "|" + datos[6];
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

    public void menuVendedor() {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        int eleccionMenuPrincipal = 0;

        System.out.println("			Bienvenido Vendedor \n");
        System.out.println("Este es su menú de opciones:");
        System.out.println("1. Crear un pedido");
        System.out.println("2. Gestionar un pedido");
        System.out.println("3. Recepción de pedidos");
        System.out.println("4. Cancelar pedido");
        System.out.println("5. Salir");
        eleccionMenuPrincipal = scan.nextInt();

        switch (eleccionMenuPrincipal) {

            case 1:
                System.out.println("Para crear un pedido, ingrese: \n");
                System.out.println("Seleccione su proveedor: ");
                operaciones.leerArchivos("proveedores.txt", "", "");
                String proveedor = scan.next();

                operaciones.realizarPedido(proveedor);

                break;

            case 2:
                System.out.println("\n Estos son todos los pedidos registrados: \n");
                operaciones.leerArchivos("pedidos.txt", "", "");
                System.out.println("\n Seleccione la opcion para filtrar: ");
                System.out.println("1. proveedor");
                System.out.println("2. fecha");
                System.out.println("3. estado");
                System.out.println("4. producto");
                int eleccion = scan.nextInt();
                String filtrado = "";

                switch (eleccion) {
                    case 1:
                        System.out.println("Ingrese el codigo del proveedor para filtrar: ");
                        filtrado = scan.next();
                        break;
                    case 2:
                        System.out.println("Ingrese la fecha para filtrar: ");
                        filtrado = scan.next();
                        break;
                    case 3:
                        System.out.println("Ingrese el estado para filtrar: ");
                        filtrado = scan.next();
                        break;
                    case 4:
                        System.out.println("Ingrese el codigo del producto para filtrar: ");
                        filtrado = scan.next();
                        break;
                }

                operaciones.filtrar("pedidos.txt", filtrado);

                System.out.println("Ingrese el codigo del pedido para ver detalles: ");
                String codigo = scan.next();
                operaciones.leerArchivos("pedidos.txt", codigo, "buscarPorCodigo");

                System.out.println("Modificar el estado del producto? si/no");
                String modificar = scan.next();
                if (modificar.equals("si")) {
                    System.out.println("Ingrese el nuevo estado: pendiente/en curso/ completado");
                    String nuevoEstado = scan.next();
                    operaciones.modificarArchivos("pedidos.txt", codigo, nuevoEstado, "cambiarEstado", "");
                }
                break;
            case 3:
                System.out.println("\n estos son los pedidos pendientes: \n");
                operaciones.filtrar("pedidos.txt", "pendiente");
                System.out.println("Ingrese el codigo del pedido: ");
                String codigoPedido = scan.next();
                operaciones.recepcionPedidos("pedidos.txt", codigoPedido);
                operaciones.llenarRecepcion("recepcion.txt", codigoPedido + "|" + "");
                break;
            case 4:
                System.out.println("\n estos son los pedidos que puede cancelar");
                operaciones.filtrar("pedidos.txt", "en curso");
                operaciones.filtrar("pedidos.txt", "pendiente");
                System.out.println("Ingrese el codigo del producto a cancelar: ");
                String productoACancelar = scan.next();
                operaciones.modificarArchivos("pedidos.txt", productoACancelar, "cancelado", "cambiarEstado", "");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        SistemaDeGestionDeInventarios operaciones = new SistemaDeGestionDeInventarios();

        operaciones.login();

    }

}
