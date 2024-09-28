package com.alanduran.spring_recipes_app.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}