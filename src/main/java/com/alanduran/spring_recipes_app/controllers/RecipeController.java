package com.alanduran.spring_recipes_app.controllers;

import com.alanduran.spring_recipes_app.command.RecipeCommand;
import com.alanduran.spring_recipes_app.domain.Difficulty;
import com.alanduran.spring_recipes_app.exceptions.NotFoundException;
import com.alanduran.spring_recipes_app.repositories.CategoryRepository;
import com.alanduran.spring_recipes_app.services.CategoryService;
import com.alanduran.spring_recipes_app.services.RecipeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

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
        model.addAttribute("categoriesList", categoryService.findAllCategoryCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult, Model model){
        log.debug("Saving new recipe");

        if(bindingResult.hasErrors()){
            model.addAttribute("categoriesList", categoryService.findAllCategoryCommand());
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        log.debug("Updating recipe with id {}", id);
        model.addAttribute("recipe", recipeService.findCommandById(id));
        model.addAttribute("categoriesList", categoryService.findAllCategoryCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        log.debug("Deleting recipe with id {}", id);
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exceptionDetails", exception);
        return modelAndView;
    }
}
