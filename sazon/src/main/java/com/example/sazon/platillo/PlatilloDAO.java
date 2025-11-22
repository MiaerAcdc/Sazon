package com.example.sazon.platillo;

import java.util.List;

public interface PlatilloDAO {

    List<Platillo> listar();

    int guardar(Platillo platillo);

    int actualizar(Platillo platillo);

    int eliminar(int id);

    Platillo obtenerPorId(int id);
}
