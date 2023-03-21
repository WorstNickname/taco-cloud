package com.akdev.tacocloud.taco.web;

import com.akdev.tacocloud.taco.data.IngredientDao;
import com.akdev.tacocloud.taco.data.JdbcIngredientRepository;
import com.akdev.tacocloud.taco.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<Integer, Ingredient> {

    private final IngredientDao ingredientDao;

    @Autowired
    public IngredientByIdConverter(JdbcIngredientRepository ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public Ingredient convert(Integer id) {
        return ingredientDao.findById(id).orElse(null);
    }
}
