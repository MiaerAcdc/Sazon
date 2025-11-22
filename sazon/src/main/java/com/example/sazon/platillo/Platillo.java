package com.example.sazon.platillo;

public class Platillo {

    private int id;
    private String nombre;
    private String descripcion;
    private int categoriaId;
    private double precio;
    private boolean condicion;

    public Platillo() {
    }

    public Platillo(int id, String nombre, String descripcion, int categoriaId, double precio, boolean condicion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
        this.precio = precio;
        this.condicion = condicion;
    }

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

    public int getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isCondicion() {
        return condicion;
    }
    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }
}
