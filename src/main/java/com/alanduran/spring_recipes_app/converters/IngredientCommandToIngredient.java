package com.alanduran.spring_recipes_app.converters;

import com.alanduran.spring_recipes_app.command.IngredientCommand;
import com.alanduran.spring_recipes_app.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(source.getUnitOfMeasure());
        ingredient.setAmount(source.getAmount());
        return ingredient;
    }
}
