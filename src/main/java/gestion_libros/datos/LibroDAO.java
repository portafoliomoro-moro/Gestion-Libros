package gestion_libros.datos;

import gestion_libros.dominio.EstadoLibro;
import gestion_libros.dominio.Libro;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static gestion_libros.conexion.Conexion.getConexion;

public class LibroDAO implements ILibroDAO{
    @Override
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM libros ORDER BY id_libro";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var libro = new Libro();
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCodigo(rs.getString("codigo"));

                String estadoBD = rs.getString("estado");
                EstadoLibro estado = EstadoLibro.valueOf(estadoBD.toUpperCase());
                libro.setEstado(estado);

                libro.setNumero_prestamos(rs.getInt("numero_prestamos"));
                libro.setNumero_renovaciones(rs.getInt("numero_renovaciones"));
                libros.add(libro);
            }
        }catch(Exception e){
            System.out.println("Error al listar los libros: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return libros;
    }

    @Override
    public boolean buscarLibroPorId(Libro libro) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM libros WHERE id_libro=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getId_libro());
            rs = ps.executeQuery();
            if(rs.next()){
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAnio(rs.getInt("anio"));
                libro.setCodigo(rs.getString("codigo"));

                String estadoBD = rs.getString("estado");
                EstadoLibro estado = EstadoLibro.valueOf(estadoBD.toUpperCase());
                libro.setEstado(estado);

                libro.setNumero_prestamos(rs.getInt("numero_prestamos"));
                libro.setNumero_renovaciones(rs.getInt("numero_renovaciones"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarLibro(Libro libro) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO libros (titulo, autor, editorial, anio, codigo, estado, " +
                " numero_prestamos, numero_renovaciones) VALUES (?,?,?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getAnio());
            ps.setString(5, libro.getCodigo());
            ps.setString(6, libro.getEstado().name().toUpperCase());
            ps.setInt(7, libro.getNumero_prestamos());
            ps.setInt(8, libro.getNumero_renovaciones());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarLibro(Libro libro) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE libros SET titulo=?, autor=?, editorial=?, anio=?, codigo=?, estado=?, numero_prestamos=?, " +
                " numero_renovaciones=? WHERE id_libro=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getAnio());
            ps.setString(5, libro.getCodigo());
            ps.setString(6, libro.getEstado().name().toUpperCase());
            ps.setInt(7, libro.getNumero_prestamos());
            ps.setInt(8, libro.getNumero_renovaciones());
            ps.setInt(9, libro.getId_libro());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al modificar libro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarLibro(Libro libro) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM libros WHERE id_libro=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getId_libro());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar libro: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ILibroDAO libroDAO = new LibroDAO();


//        System.out.println("*** Buscar ***");
//        var buscar = new Libro(1);
//        var encontrado = libroDAO.buscarLibroPorId(buscar);
//        if(encontrado){
//            System.out.println("Libro encontrado: " + buscar);
//        }
//        else{
//            System.out.println("Libro No encontrado: " + buscar);
//        }
//        System.out.println("*** Agregar ***");
//        var agregar = new Libro("Crimen y Catigo", "Fiodor Dostoeksky", "bolsillo", 1890, "FH001", EstadoLibro.PRESTADO, 1, 0);
//        var agregado = libroDAO.agregarLibro(agregar);
//        if(agregado){
//            System.out.println("Libro agregado: " + agregar);
//        }
//        else{
//            System.out.println("Libro No agregado: " + agregar);
//        }

//        System.out.println("*** Modificar ***");
//        var modificar = new Libro(2, "Crimen y Castigo", "Fedor Dostoeksky", "Bolsillo", 1890, "FH002", EstadoLibro.DISPONIBLE, 2, 1);
//        var moddoficao = libroDAO.modificarLibro(modificar);
//        if(moddoficao){
//            System.out.println("Libro modificado: " + modificar);
//        }
//        else{
//            System.out.println("Libro No modificado: " + modificar);
//        }
        System.out.println("*** Eliminar ***");
        var elimianr = new Libro(2);
        var elimiando = libroDAO.eliminarLibro(elimianr);
        if(elimiando){
            System.out.println("Libro eliminado: " + elimianr);
        }
        else{
            System.out.println("Libro No eliminado: " + elimianr);
        }

        System.out.println("*** Listar Libros ***");
        var listar = libroDAO.listarLibros();
        listar.forEach(System.out::println);
    }
}
