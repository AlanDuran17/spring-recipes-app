package com.alanduran.spring_recipes_app.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    static Category category;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        category = new Category();
    }

    @Test
    void getId() {
        category.setId(1L);
        assertEquals(1L, category.getId());
    }

    @Test
    void getDescription() {
        category.setDescription("test");
        assertEquals("test", category.getDescription());
    }

    @Test
    void getRecipes() {
        category.setRecipes(new HashSet<>());
        assertEquals(new HashSet<>(), category.getRecipes());
    }
}