package com.example.sazon.categoria;

import java.util.List;

public interface CategoriaDAO {

    List<Categoria> listar();

    int guardar(Categoria categoria);

    int actualizar(Categoria categoria);

    int eliminar(int id);

    Categoria obtenerPorId(int id);
}


