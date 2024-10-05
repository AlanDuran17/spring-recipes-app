package com.alanduran.spring_recipes_app.repositories;

import com.alanduran.spring_recipes_app.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
