package com.akdev.tacocloud.taco.data;

import com.akdev.tacocloud.taco.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class JdbcIngredientRepository implements IngredientDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        String selectByIdSQL = """
                SELECT id, name, type 
                FROM ingredient 
                WHERE id=?
                """;
        var results = jdbcTemplate.query(selectByIdSQL,
                this::mapRowToIngredient,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public List<Ingredient> findAll() {
        String selectAllSQL = """
                SELECT id, name, type 
                FROM ingredient
                """;
        return jdbcTemplate.query(selectAllSQL, this::mapRowToIngredient);
    }

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient) {
        String saveSQL = """
                INSERT INTO ingredient (id, name, type)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(saveSQL,
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }

}
