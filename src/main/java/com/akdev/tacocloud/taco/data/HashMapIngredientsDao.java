package com.akdev.tacocloud.taco.data;

import com.akdev.tacocloud.taco.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("map")
public class HashMapIngredientsDao implements IngredientDao {

    private final IngredientsSource ingredientsSource;

    @Autowired
    public HashMapIngredientsDao(IngredientsSource ingredientsSource) {
        this.ingredientsSource = ingredientsSource;
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        return Optional.ofNullable(ingredientsSource.getIngredientMap().get(id));
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientsSource.getIngredientMap()
                .values()
                .stream()
                .toList();
    }

    @Override
    public Ingredient save(Ingredient entity) {
        ingredientsSource.getIngredientMap().put(entity.getId(), entity);
        return entity;
    }

}
