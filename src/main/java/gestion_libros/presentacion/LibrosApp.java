package gestion_libros.presentacion;

import gestion_libros.datos.ILibroDAO;
import gestion_libros.datos.LibroDAO;
import gestion_libros.dominio.EstadoLibro;
import gestion_libros.dominio.Libro;

import java.util.Scanner;

public class LibrosApp {
    public static void main(String[] args) {
        libroApp();
    }

    private static void libroApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        ILibroDAO libroDAO = new LibroDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(opcion, consola, libroDAO);
            }catch(Exception e){
                System.out.println("Error al ingresar opción: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Gestión Libros ***
                1. Listar Libros
                2. Buscar Libro
                3. Agregar Libro
                4. Modificar Libro
                5. Eliminar Libro
                6. Salir
                Elige una opción:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner consola, ILibroDAO libroDAO){
        var salir = false;
        switch(opcion){
            case 1 -> {
                System.out.println("--- Listado de Libros ---");
                var listarLibros = libroDAO.listarLibros();
                listarLibros.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("--- Buscar Libro ---");
                System.out.print("Id del libro a buscar: ");
                var idLibro = Integer.parseInt(consola.nextLine());
                var buscarLibro = new Libro(idLibro);
                var encontrado = libroDAO.buscarLibroPorId(buscarLibro);
                if(encontrado){
                    System.out.println("Libro encontrado: " + buscarLibro);
                }
                else{
                    System.out.println("Libro No encontrado: " + buscarLibro);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar Libro ---");
                System.out.print("Titulo: ");
                var titulo = consola.nextLine();
                System.out.print("Autor: ");
                var autor = consola.nextLine();
                System.out.print("Editorial: ");
                var editorial = consola.nextLine();
                System.out.print("Año: ");
                var anio = Integer.parseInt(consola.nextLine());
                System.out.print("Código: ");
                var codigo = consola.nextLine();
                System.out.print("Estado (PRESTADO, DISPONIBLE, DAÑADO): ");
                String estadoInput = consola.nextLine().trim().toUpperCase().replace("Ñ", "N");

                EstadoLibro estado;
                try {
                    estado = EstadoLibro.valueOf(estadoInput);
                } catch (Exception e) {
                    System.out.println("Estado inválido. Se asignará DISPONIBLE por defecto.");
                    estado = EstadoLibro.DISPONIBLE;
                }
                System.out.print("Número de préstamos: ");
                var numeroPrestamo = Integer.parseInt(consola.nextLine());
                System.out.print("Número de renovaciones: ");
                var numeroRenovacion = Integer.parseInt(consola.nextLine());
                var agregarLibro = new Libro(titulo, autor, editorial, anio,codigo, estado, numeroPrestamo, numeroRenovacion);
                var agregado = libroDAO.agregarLibro(agregarLibro);
                if(agregado){
                    System.out.println("Libro agregado: " + agregarLibro);
                }
                else{
                    System.out.println("Libro No agregado: " + agregarLibro);
                }
            }
            case 4 -> {
                System.out.println("--- Modificar Libro ---");
                System.out.print("Id del libro a modificar: ");
                var idLibro = Integer.parseInt(consola.nextLine());
                var buscarLibro = new Libro(idLibro);
                var encontrado = libroDAO.buscarLibroPorId(buscarLibro);
                if(encontrado){
                    System.out.print("Titulo: ");
                    var titulo = consola.nextLine();
                    System.out.print("Autor: ");
                    var autor = consola.nextLine();
                    System.out.print("Editorial: ");
                    var editorial = consola.nextLine();
                    System.out.print("Año: ");
                    var anio = Integer.parseInt(consola.nextLine());
                    System.out.print("Código: ");
                    var codigo = consola.nextLine();
                    System.out.print("Estado (PRESTADO, DISPONIBLE, DAÑADO): ");
                    String estadoInput = consola.nextLine().trim().toUpperCase().replace("Ñ", "N");

                    EstadoLibro estado;
                    try {
                        estado = EstadoLibro.valueOf(estadoInput);
                    } catch (Exception e) {
                        System.out.println("Estado inválido. Se asignará DISPONIBLE por defecto.");
                        estado = EstadoLibro.DISPONIBLE;
                    }
                    System.out.print("Número de préstamos: ");
                    var numeroPrestamo = Integer.parseInt(consola.nextLine());
                    System.out.print("Número de renovaciones: ");
                    var numeroRenovacion = Integer.parseInt(consola.nextLine());
                    var modificarLibro = new Libro(idLibro, titulo, autor, editorial, anio,codigo, estado, numeroPrestamo, numeroRenovacion);
                    var modificado = libroDAO.modificarLibro(modificarLibro);
                    System.out.println("Libro Modificado: " + modificarLibro);
                }
                else{
                    System.out.println("Libro No encontrado: " + buscarLibro);
                }
            }
            case 5 -> {
                System.out.println("--- Eliminar Libro ---");
                System.out.print("Id del libro a eliminar: ");
                var idLibro = Integer.parseInt(consola.nextLine());
                var buscar = new Libro(idLibro);
                var encontrado = libroDAO.buscarLibroPorId(buscar);
                if(encontrado){
                    libroDAO.eliminarLibro(buscar);
                    System.out.println("Libro eliminado: " + buscar);
                }
                else{
                    System.out.println("Libro No encontrado: " + buscar);
                }

            }
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción No reconocida: " + opcion);
        }
        return salir;
    }
}

