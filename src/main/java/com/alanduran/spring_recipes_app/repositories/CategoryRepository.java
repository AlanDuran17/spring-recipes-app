package com.alanduran.spring_recipes_app.repositories;

import com.alanduran.spring_recipes_app.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
