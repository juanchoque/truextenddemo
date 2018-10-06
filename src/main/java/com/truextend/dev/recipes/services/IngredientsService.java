package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Ingredients;

import java.util.HashMap;

public interface IngredientsService {
    HashMap deteleIngredients(Ingredients ingredients);

    HashMap getAllIngredients(Ingredients ingredients);

    HashMap getIngredientsById(Ingredients ingredients);

    HashMap saveOrUpdateIngredients(Ingredients ingredients);

}
