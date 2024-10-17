package com.alanduran.spring_recipes_app.converters;

import com.alanduran.spring_recipes_app.command.RecipeCommand;
import com.alanduran.spring_recipes_app.domain.Category;
import com.alanduran.spring_recipes_app.domain.Notes;
import com.alanduran.spring_recipes_app.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter, IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(source.getNotes() != null ? notesConverter.convert(source.getNotes()) : new Notes());

        if (source.getCategories() != null && !source.getCategories().isEmpty()){
            recipe.setCategories(source.getCategories().stream()
                    .map(aLong -> {
                        Category category = new Category();
                        category.setId(aLong);
                        return category;
                    })
                    .collect(Collectors.toSet()));
        }

        if (source.getIngredients() != null && !source.getIngredients().isEmpty()){
            recipe.setIngredients(source.getIngredients().stream()
                    .map(ingredientConverter::convert)
                    .collect(Collectors.toSet()));
        }

        return recipe;
    }
}
