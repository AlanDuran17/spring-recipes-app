package com.alanduran.spring_recipes_app.controllers;

import com.alanduran.spring_recipes_app.domain.Category;
import com.alanduran.spring_recipes_app.domain.UnitOfMeasure;
import com.alanduran.spring_recipes_app.repositories.CategoryRepository;
import com.alanduran.spring_recipes_app.repositories.UnitOfMeasureRepository;
import com.alanduran.spring_recipes_app.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }
}
