package com.example.sazon.categoria;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaDAO.guardar(categoria);
    }

    @Override
    public void actualizar(Categoria categoria) {
        categoriaDAO.actualizar(categoria);
    }

    @Override
    public void eliminar(int id) {
        int result = categoriaDAO.eliminar(id);
        if (result == -1) {
            throw new IllegalStateException("No se puede eliminar la categoría porque está en uso por uno o más platillos.");
        }
    }


    @Override
    public Categoria obtenerPorId(int id) {
        return categoriaDAO.obtenerPorId(id);
    }
}
