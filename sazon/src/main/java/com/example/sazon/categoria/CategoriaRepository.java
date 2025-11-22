package com.example.sazon.categoria;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepository implements CategoriaDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Categoria> categoriaRowMapper = (rs, rowNum) ->
            new Categoria(
                    rs.getInt("Id"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getBoolean("Condicion")
            );

    @Override
    public List<Categoria> listar() {
        String sql = "SELECT * FROM Categoria";
        return jdbcTemplate.query(sql, categoriaRowMapper);
    }

    @Override
    public int guardar(Categoria categoria) {
        String sql = "INSERT INTO Categoria (Nombre, Descripcion, Condicion) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.isCondicion());
    }

    @Override
    public int actualizar(Categoria categoria) {
        String sql = "UPDATE Categoria SET Nombre = ?, Descripcion = ?, Condicion = ? WHERE Id = ?";
        return jdbcTemplate.update(sql,
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.isCondicion(),
                categoria.getId());
    }

    @Override
    public int eliminar(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM Platillos WHERE Categoria_Id = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, id);

        if (count != null && count > 0) {
            return -1;
        }

        String sql = "DELETE FROM Categoria WHERE Id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Categoria obtenerPorId(int id) {
        String sql = "SELECT * FROM Categoria WHERE Id = ?";
        List<Categoria> lista = jdbcTemplate.query(sql, categoriaRowMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }
}
