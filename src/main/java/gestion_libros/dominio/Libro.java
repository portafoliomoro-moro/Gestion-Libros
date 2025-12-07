package gestion_libros.dominio;

import java.util.Objects;

public class Libro {
    private int id_libro;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private String codigo;
    private EstadoLibro estado;
    private int numero_prestamos;
    private int numero_renovaciones;


    public Libro(){}

    // Para poder buscar y eliminar un libro
    public Libro(int id_libro){
        this.id_libro = id_libro;
    }

    // Para poder agregar un libro
    public Libro(String titulo, String autor, String editorial, int anio, String codigo, EstadoLibro estado,
                 int numero_prestamos, int numero_renovaciones){
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.codigo = codigo;
        this.estado = estado;
        this.numero_prestamos = numero_prestamos;
        this.numero_renovaciones = numero_renovaciones;
    }

    // Para poder modificar un libro
    public Libro(int id_libro, String titulo, String autor, String editorial, int anio, String codigo, EstadoLibro estado,
                 int numero_prestamos, int numero_renovaciones){
        this(titulo,autor,editorial,anio,codigo,estado,numero_prestamos,numero_renovaciones);
        this.id_libro = id_libro;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    public int getNumero_prestamos() {
        return numero_prestamos;
    }

    public void setNumero_prestamos(int numero_prestamos) {
        this.numero_prestamos = numero_prestamos;
    }

    public int getNumero_renovaciones() {
        return numero_renovaciones;
    }

    public void setNumero_renovaciones(int numero_renovaciones) {
        this.numero_renovaciones = numero_renovaciones;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anio=" + anio +
                ", codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", numero_prestamos=" + numero_prestamos +
                ", numero_renovaciones=" + numero_renovaciones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id_libro == libro.id_libro && anio == libro.anio && numero_prestamos == libro.numero_prestamos && numero_renovaciones == libro.numero_renovaciones && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor) && Objects.equals(editorial, libro.editorial) && Objects.equals(codigo, libro.codigo) && estado == libro.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_libro, titulo, autor, editorial, anio, codigo, estado, numero_prestamos, numero_renovaciones);
    }
}

