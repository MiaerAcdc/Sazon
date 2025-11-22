package com.example.sazon.categoria;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean condicion; // nuevo atributo

    public Categoria() {
    }

    public Categoria(int id, String nombre, String descripcion, boolean condicion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCondicion() {
        return condicion;
    }
    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }
}
