package com.alanduran.spring_recipes_app.repositories;

import com.alanduran.spring_recipes_app.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
