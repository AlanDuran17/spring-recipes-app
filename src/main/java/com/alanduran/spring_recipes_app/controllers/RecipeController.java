package com.alanduran.spring_recipes_app.controllers;

import com.alanduran.spring_recipes_app.command.RecipeCommand;
import com.alanduran.spring_recipes_app.domain.Difficulty;
import com.alanduran.spring_recipes_app.repositories.CategoryRepository;
import com.alanduran.spring_recipes_app.services.CategoryService;
import com.alanduran.spring_recipes_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        log.debug("Creating new recipe");
        model.addAttribute("recipe", new RecipeCommand());
        model.addAttribute("difficultyList", Difficulty.values());
        model.addAttribute("categoriesList", categoryService.findAllCategoryCommand());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        log.debug("Saving new recipe");
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        log.debug("Updating recipe with id {}", id);
        model.addAttribute("recipe", recipeService.findCommandById(id));
        model.addAttribute("difficultyList", Difficulty.values());
        model.addAttribute("categoriesList", categoryService.findAllCategoryCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        log.debug("Deleting recipe with id {}", id);
        recipeService.deleteById(id);
        return "redirect:/";
    }


}
