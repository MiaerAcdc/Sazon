package com.example.sazon.venta;

import com.example.sazon.platillo.Platillo;
import com.example.sazon.platillo.PlatilloService;
import com.example.sazon.categoria.Categoria;
import com.example.sazon.categoria.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaDAO ventaDAO;
    private final PlatilloService platilloService;
    private final CategoriaService categoriaService;

    public VentaServiceImpl(VentaDAO ventaDAO, PlatilloService platilloService, CategoriaService categoriaService) {
        this.ventaDAO = ventaDAO;
        this.platilloService = platilloService;
        this.categoriaService = categoriaService;
    }

    @Override
    @Transactional
    public int crearVentaConDetalles(Venta venta, int[] platilloIds, int[] cantidades) {
        validarPlatillos(platilloIds, cantidades);

        venta.setTipo("Local");
        venta.setEstado("Entregado"); // Asumiendo que las ventas locales se completan al instante
        venta.setFecha(LocalDateTime.now());

        int ventaId = ventaDAO.guardarVenta(venta);

        for (int i = 0; i < platilloIds.length; i++) {
            DetalleVenta dv = new DetalleVenta(0, ventaId, platilloIds[i], cantidades[i]);
            ventaDAO.guardarDetalle(dv);
        }
        return ventaId;
    }

    @Override
    @Transactional
    public void actualizarVentaConDetalles(Venta venta, int[] platilloIds, int[] cantidades) {

        venta.setTipo("Local");
        // El estado se mantiene si fue enviado desde el formulario de edición

        validarPlatillos(platilloIds, cantidades);

        ventaDAO.actualizarVenta(venta);

        ventaDAO.eliminarDetallesPorVenta(venta.getId());

        for (int i = 0; i < platilloIds.length; i++) {
            DetalleVenta dv = new DetalleVenta(0, venta.getId(), platilloIds[i], cantidades[i]);
            ventaDAO.guardarDetalle(dv);
        }
    }

    @Override
    @Transactional
    public void eliminarVentaConDetalles(int ventaId) {
        ventaDAO.eliminarDetallesPorVenta(ventaId);
        ventaDAO.eliminarVenta(ventaId);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaDAO.listarVentas();
    }

    @Override
    public List<Venta> listarVentasLocales() {
        return ventaDAO.listarVentasPorTipo("Local");
    }

    @Override
    public List<DetalleVenta> listarDetallesPorVenta(int ventaId) {
        return ventaDAO.listarDetallesPorVenta(ventaId);
    }

    @Override
    public Venta obtenerVentaPorId(int id) {
        return ventaDAO.obtenerVentaPorId(id);
    }

    private void validarPlatillos(int[] platilloIds, int[] cantidades) {
        if (platilloIds.length != cantidades.length) {
            throw new IllegalArgumentException("Datos inconsistentes en platillos y cantidades.");
        }

        // 🚨 Validación para asegurar que se intentó guardar al menos un platillo
        if (platilloIds.length == 0) {
            throw new IllegalArgumentException("Debe seleccionar al menos un platillo y una cantidad válida.");
        }

        // El Controller ya ha filtrado los IDs <= 0, solo validamos la condición de los activos
        for (int platilloId : platilloIds) {
            Platillo p = platilloService.obtenerPorId(platilloId);
            if (p == null) {
                throw new IllegalArgumentException("Platillo no encontrado (ID: " + platilloId + ").");
            }
            Categoria c = categoriaService.obtenerPorId(p.getCategoriaId());
            if (!p.isCondicion() || !c.isCondicion()) {
                throw new IllegalArgumentException("Platillo o categoría inactiva: " + p.getNombre());
            }
        }
    }
}