package com.example.sazon.platillo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlatilloRepository implements PlatilloDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlatilloRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Platillo> platilloRowMapper = (rs, rowNum) ->
            new Platillo(
                    rs.getInt("Id"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getInt("Categoria"),
                    rs.getDouble("Precio"),
                    rs.getBoolean("Condicion")
            );

    @Override
    public List<Platillo> listar() {
        String sql = "SELECT * FROM Platillo";
        return jdbcTemplate.query(sql, platilloRowMapper);
    }

    @Override
    public int guardar(Platillo platillo) {
        String sql = "INSERT INTO Platillo (Nombre, Descripcion, Categoria, Precio, Condicion) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                platillo.getNombre(),
                platillo.getDescripcion(),
                platillo.getCategoriaId(),
                platillo.getPrecio(),
                platillo.isCondicion());
    }

    @Override
    public int actualizar(Platillo platillo) {
        String sql = "UPDATE Platillo SET Nombre=?, Descripcion=?, Categoria=?, Precio=?, Condicion=? WHERE Id=?";
        return jdbcTemplate.update(sql,
                platillo.getNombre(),
                platillo.getDescripcion(),
                platillo.getCategoriaId(),
                platillo.getPrecio(),
                platillo.isCondicion(),
                platillo.getId());
    }

    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM Platillo WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Platillo obtenerPorId(int id) {
        String sql = "SELECT * FROM Platillo WHERE Id = ?";
        List<Platillo> lista = jdbcTemplate.query(sql, platilloRowMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }
}
