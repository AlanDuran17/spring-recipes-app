package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
