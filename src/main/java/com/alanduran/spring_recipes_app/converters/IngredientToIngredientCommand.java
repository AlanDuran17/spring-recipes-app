package com.alanduran.spring_recipes_app.converters;

import com.alanduran.spring_recipes_app.command.CategoryCommand;
import com.alanduran.spring_recipes_app.command.IngredientCommand;
import com.alanduran.spring_recipes_app.domain.Category;
import com.alanduran.spring_recipes_app.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }
        final IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(source.getId());
        if (source.getRecipe() != null) {
            ingredient.setRecipeId(source.getRecipe().getId());
        }
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        ingredient.setAmount(source.getAmount());
        return ingredient;
    }
}
