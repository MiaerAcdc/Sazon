package com.example.sazon.platillo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlatilloServiceImpl implements PlatilloService {

    private final PlatilloDAO platilloDAO;

    public PlatilloServiceImpl(PlatilloDAO platilloDAO) {
        this.platilloDAO = platilloDAO;
    }

    @Override
    public List<Platillo> listar() {
        return platilloDAO.listar();
    }

    @Override
    public void guardar(Platillo platillo) {
        platilloDAO.guardar(platillo);
    }

    @Override
    public void actualizar(Platillo platillo) {
        platilloDAO.actualizar(platillo);
    }

    @Override
    public void eliminar(int id) {
        platilloDAO.eliminar(id);
    }

    @Override
    public Platillo obtenerPorId(int id) {
        return platilloDAO.obtenerPorId(id);
    }
}
