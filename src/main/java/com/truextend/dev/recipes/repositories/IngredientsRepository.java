package com.truextend.dev.recipes.repositories;

import com.truextend.dev.recipes.model.Ingredients;
import com.truextend.dev.recipes.model.Recipes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IngredientsRepository extends CrudRepository<Ingredients, Integer> {

    @Query("FROM Ingredients a where a.recipes.id = :#{#recipes.id}")
    List<Ingredients> findAllByRecipes(@Param("recipes")Recipes recipes);

    @Modifying
    @Query("DELETE Ingredients a where a.recipes.id = :#{#recipes.id}")
    @Transactional
    void deleteAllByRecipes(@Param("recipes")Recipes recipes);
}
