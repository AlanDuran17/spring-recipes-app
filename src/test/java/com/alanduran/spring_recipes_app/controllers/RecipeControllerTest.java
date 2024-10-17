package com.alanduran.spring_recipes_app.controllers;


import com.alanduran.spring_recipes_app.domain.Recipe;
import com.alanduran.spring_recipes_app.services.CategoryService;
import com.alanduran.spring_recipes_app.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @InjectMocks
    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    CategoryService categoryService;

    @Test
    void testShowRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

}
