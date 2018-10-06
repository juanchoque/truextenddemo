package com.truextend.dev.recipes.repositories;

import com.truextend.dev.recipes.model.Recipes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RecipesRepository extends CrudRepository<Recipes, Integer> {
    @Modifying
    @Query("UPDATE Recipes r SET r.state = :#{#recipes.state} where r.id = :#{#recipes.id}")
    @Transactional
    void deleteRecipes(@Param("recipes") Recipes recipes);
}