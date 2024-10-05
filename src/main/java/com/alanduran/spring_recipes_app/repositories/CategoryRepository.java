package com.alanduran.spring_recipes_app.repositories;

import com.alanduran.spring_recipes_app.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
