package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.domain.Recipe;
import com.alanduran.spring_recipes_app.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Test
    public void getAllRecipes_EmptyData() {
        Set<Recipe> recipeSet = recipeService.getAllRecipes();
        assertEquals( 0, recipeSet.size());
    }

    @Test
    public void getAllRecipes_WithMockedData() {
        HashSet<Recipe> expected = new HashSet<>();
        expected.add(new Recipe());
        expected.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(expected);
        Set<Recipe> actual = recipeService.getAllRecipes();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
        verify(recipeRepository, times(1)).findAll();
    }
}