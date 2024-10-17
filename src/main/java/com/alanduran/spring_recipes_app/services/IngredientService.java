package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.command.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
}
