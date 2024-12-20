package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.domain.Recipe;
import com.alanduran.spring_recipes_app.exceptions.NotFoundException;
import com.alanduran.spring_recipes_app.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
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

    @Test
    public void findById() {
        Recipe expected = new Recipe();
        expected.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        Recipe actual = recipeService.findById(1L);

        assertEquals(expected, actual);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void findByIdNotFound() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> recipeService.findById(1L));
    }

    @Test
    public void testDeleteById() {
        Long idToDelete = 2L;

        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}