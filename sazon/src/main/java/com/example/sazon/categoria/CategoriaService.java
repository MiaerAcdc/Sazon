package com.example.sazon.categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listar();

    void guardar(Categoria categoria);

    void actualizar(Categoria categoria);

    void eliminar(int id);

    Categoria obtenerPorId(int id);
}
