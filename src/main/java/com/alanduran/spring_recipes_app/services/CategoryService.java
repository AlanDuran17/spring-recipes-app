package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.command.CategoryCommand;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Set<CategoryCommand> findAllCategoryCommand();
}
