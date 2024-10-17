package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.command.CategoryCommand;
import com.alanduran.spring_recipes_app.converters.CategoryToCategoryCommand;
import com.alanduran.spring_recipes_app.domain.Category;
import com.alanduran.spring_recipes_app.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<CategoryCommand> findAllCategoryCommand() {
        Set<Category> categoriesSet = new HashSet<>();
        categoryRepository.findAll().forEach(categoriesSet::add);
        return categoriesSet.stream()
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toSet());
    }
}
