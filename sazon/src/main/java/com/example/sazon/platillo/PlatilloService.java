package com.example.sazon.platillo;

import java.util.List;

public interface PlatilloService {

    List<Platillo> listar();

    void guardar(Platillo platillo);

    void actualizar(Platillo platillo);

    void eliminar(int id);

    Platillo obtenerPorId(int id);
}
