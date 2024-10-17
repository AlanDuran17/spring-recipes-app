package com.alanduran.spring_recipes_app.converters;

import com.alanduran.spring_recipes_app.command.CategoryCommand;
import com.alanduran.spring_recipes_app.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand category = new CategoryCommand();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
