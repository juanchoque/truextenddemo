package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Recipes;

import java.util.HashMap;

public interface RecipesService {
    HashMap deteleRecipes(Recipes recipes);

    HashMap getAllRecipes(Recipes recipes);

    HashMap getRecipesById(Recipes recipes);

    HashMap getRecipesByAccounts(Recipes recipes);

    HashMap saveOrUpdateRecipes(Recipes recipes);

}
