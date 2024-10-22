package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.command.RecipeCommand;
import com.alanduran.spring_recipes_app.converters.RecipeCommandToRecipe;
import com.alanduran.spring_recipes_app.converters.RecipeToRecipeCommand;
import com.alanduran.spring_recipes_app.domain.Recipe;
import com.alanduran.spring_recipes_app.exceptions.NotFoundException;
import com.alanduran.spring_recipes_app.repositories.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        log.debug("Creating RecipeServiceImpl");
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("Getting all recipes");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        log.debug("Getting recipe by ID: {}", id);
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found for ID: " + id));
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        log.debug("Getting recipe command by ID: {}", id);

        return recipeRepository.findById(id)
                .map(recipeToRecipeCommand::convert)
                .orElseThrow(() -> new NotFoundException("Recipe not found for ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: {}", savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
