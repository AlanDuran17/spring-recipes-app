package com.alanduran.spring_recipes_app.bootstrap;

import com.alanduran.spring_recipes_app.domain.*;
import com.alanduran.spring_recipes_app.repositories.CategoryRepository;
import com.alanduran.spring_recipes_app.repositories.RecipeRepository;
import com.alanduran.spring_recipes_app.repositories.UnitOfMeasureRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public List<Recipe> createRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
        recipes.add(getGuacamoleRecipe());
        recipes.add(getTacosAlPastorRecipe());
        return recipes;
    }

    private Recipe getGuacamoleRecipe() {
        log.debug("Creating Guacamole Recipe");
        Recipe guacamole = new Recipe();

        Category mexicanCategory = categoryRepository.findByDescription("Mexican").orElseThrow(() -> new RuntimeException("Expected Category Not Found"));

        guacamole.getCategories().add(mexicanCategory);
        guacamole.setDescription("Mexican guacamole");
        guacamole.setDirections("1. Cut the avocados \n2. Mash the avocado flesh \n3. Add the remaining ingredients to taste \n4. Serve immediately");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(3);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.MODERATE);

        UnitOfMeasure eachUOM = unitOfMeasureRepository.findByDescription("Each").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure teaSpoonUOM = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure tableSpoonUOM = unitOfMeasureRepository.findByDescription("Tablespoon").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure pinchUOM = unitOfMeasureRepository.findByDescription("Pinch").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));

        guacamole.addIngredient(new Ingredient("Ripe avocados", BigDecimal.valueOf(2), eachUOM));
        guacamole.addIngredient(new Ingredient("Kosher salt", BigDecimal.valueOf(0.25), teaSpoonUOM));
        guacamole.addIngredient(new Ingredient("Fresh lime or lemon juice", BigDecimal.valueOf(1), tableSpoonUOM));
        guacamole.addIngredient(new Ingredient("Red onion or thinly sliced green onion", BigDecimal.valueOf(4), tableSpoonUOM));
        guacamole.addIngredient(new Ingredient("Serrano (or jalapeño) chilis, stems and seeds removed, minced", BigDecimal.valueOf(2), eachUOM));
        guacamole.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tableSpoonUOM));
        guacamole.addIngredient(new Ingredient("Freshly ground black pepper", BigDecimal.valueOf(1), pinchUOM));
        guacamole.addIngredient(new Ingredient("Ripe tomato. chopped", BigDecimal.valueOf(0.5), eachUOM));
        guacamole.addIngredient(new Ingredient("Red radish or jicama slices for garnish", BigDecimal.valueOf(1), eachUOM));
        guacamole.addIngredient(new Ingredient("Tortilla chips, to serve", BigDecimal.valueOf(1), eachUOM));

        guacamole.setNotes(new Notes(guacamole, "Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards."));
        return guacamole;
    }

    private Recipe getTacosAlPastorRecipe() {
        log.debug("Creating Tacos al Pastor Recipe");
        Recipe tacosAlPastor = new Recipe();

        Category mexicanCategory = categoryRepository.findByDescription("Mexican").orElseThrow(() -> new RuntimeException("Expected Category Not Found"));
        Category fastFoodCategory = categoryRepository.findByDescription("Fast Food").orElseThrow(() -> new RuntimeException("Expected Category Not Found"));

        tacosAlPastor.getCategories().add(mexicanCategory);
        tacosAlPastor.getCategories().add(fastFoodCategory);

        tacosAlPastor.setDescription("Tacos al Pastor");
        tacosAlPastor.setDirections(
                "1. Rehydrate the chiles: Boil 3 cups of water and soak the chiles for 20 minutes.\n" +
                        "2. Prepare the pineapple: Cut into chunks and reserve some for roasting.\n" +
                        "3. Make the adobada marinade: Blend the soaked chiles, pineapple chunks, garlic, achiote paste, and spices.\n" +
                        "4. Marinate the pork: Coat the pork in the marinade and refrigerate for at least 3 hours.\n" +
                        "5. Sear the pork: Sear in batches for 2 minutes on each side, then bake at 400°F for 20 minutes.\n" +
                        "6. Roast the pineapple: Roast the reserved pineapple until lightly charred.\n" +
                        "7. Warm the tortillas and slice the pork.\n" +
                        "8. Assemble the tacos: Add pork, pineapple, onions, cilantro, and lime to the tortillas."
        );
        tacosAlPastor.setPrepTime(30);
        tacosAlPastor.setCookTime(60);
        tacosAlPastor.setServings(5);
        tacosAlPastor.setSource("Simply Recipes");
        tacosAlPastor.setUrl("https://www.simplyrecipes.com/tacos-al-pastor-recipe-5496915");
        tacosAlPastor.setDifficulty(Difficulty.MODERATE);

        UnitOfMeasure cupUOM = unitOfMeasureRepository.findByDescription("Cup").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure eachUOM = unitOfMeasureRepository.findByDescription("Each").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure tablespoonUOM = unitOfMeasureRepository.findByDescription("Tablespoon").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure teaspoonUOM = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));
        UnitOfMeasure poundUOM = unitOfMeasureRepository.findByDescription("Pound").orElseThrow(() -> new RuntimeException("Expected UnitOfMeasure Not Found"));


        tacosAlPastor.addIngredient(new Ingredient("Water", BigDecimal.valueOf(3), cupUOM));
        tacosAlPastor.addIngredient(new Ingredient("Dried ancho chiles", BigDecimal.valueOf(2), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Dried pasilla chile", BigDecimal.valueOf(1), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Large ripe pineapple", BigDecimal.valueOf(1), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Garlic cloves", BigDecimal.valueOf(3), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Achiote paste", BigDecimal.valueOf(1), tablespoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Ground cumin", BigDecimal.valueOf(1), teaspoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Black peppercorns", BigDecimal.valueOf(1), teaspoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Apple cider vinegar", BigDecimal.valueOf(0.33), cupUOM));
        tacosAlPastor.addIngredient(new Ingredient("Olive oil", BigDecimal.valueOf(3), tablespoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Orange juice", BigDecimal.valueOf(3), tablespoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Kosher salt", BigDecimal.valueOf(1), teaspoonUOM));
        tacosAlPastor.addIngredient(new Ingredient("Pork shoulder steaks", BigDecimal.valueOf(3), poundUOM));
        tacosAlPastor.addIngredient(new Ingredient("White onion, finely diced", BigDecimal.valueOf(1), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Cilantro, finely chopped", BigDecimal.valueOf(1), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Limes", BigDecimal.valueOf(3), eachUOM));
        tacosAlPastor.addIngredient(new Ingredient("Corn tortillas", BigDecimal.valueOf(20), eachUOM));

        tacosAlPastor.setNotes(new Notes(tacosAlPastor, "For best results, let the pork marinate overnight, and handle chiles with care."));

        return tacosAlPastor;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(createRecipes());
    }
}
