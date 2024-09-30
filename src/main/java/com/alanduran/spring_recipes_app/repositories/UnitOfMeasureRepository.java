package com.alanduran.spring_recipes_app.repositories;

import com.alanduran.spring_recipes_app.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
