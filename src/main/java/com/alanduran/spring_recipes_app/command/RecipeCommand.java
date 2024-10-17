package com.alanduran.spring_recipes_app.command;

import com.alanduran.spring_recipes_app.domain.Category;
import com.alanduran.spring_recipes_app.domain.Difficulty;
import com.alanduran.spring_recipes_app.domain.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private Set<Long> categories = new HashSet<>();
    private NotesCommand notes;
}
