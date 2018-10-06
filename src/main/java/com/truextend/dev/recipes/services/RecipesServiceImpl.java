package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Recipes;
import com.truextend.dev.recipes.repositories.RecipesRepository;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RecipesServiceImpl implements RecipesService {

    @Autowired
    private RecipesRepository recipesRepository;

    /**
     * method that delete a recipes in data base
     * @param recipes
     * @return
     */
    @Override
    public HashMap deteleRecipes(Recipes recipes) {
        String messageTemp = "";
        boolean status = false;

        Recipes editRecipes = null;
        HashMap resultMap = new HashMap();

        try {
            editRecipes = this.recipesRepository.findOne(recipes.getId());
            if(editRecipes != null){
                //logical deleting only
                editRecipes.setState(ConstantsRecipes.STATE_INACTIVE);
                this.recipesRepository.deleteRecipes(editRecipes);
                resultMap.put(ConstantsRecipes.OBJECT, editRecipes);
                status = true;
            }
            else{
                messageTemp = ConstantsRecipes.MESSAGE_NOT_FOUND_OBJECT;
            }
        }catch (Exception er){
            messageTemp = er.getMessage();
            status = false;
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }

    /**
     * Method get list recipes from data base
     * @param recipes
     * @return
     */
    @Override
    public HashMap getAllRecipes(Recipes recipes) {
        String messageTemp = "";
        boolean status = false;

        List<Recipes> listRecipes = new ArrayList<>();
        HashMap resultMap = new HashMap();

        try {
            if(recipes == null){
                recipes = new Recipes();
            }
            recipes.setState(ConstantsRecipes.STATE_ACTIVE);

            this.recipesRepository.findAll().forEach(listRecipes::add);
            if(listRecipes != null){
                resultMap.put(ConstantsRecipes.OBJECT, listRecipes);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);
        return resultMap;
    }

    /**
     * Methdo get an account by id
     * @param recipes
     * @return
     */
    @Override
    public HashMap getRecipesById(Recipes recipes) {
        String messageTemp = "";
        boolean status = false;

        Recipes resultRecipes = null;
        HashMap resultMap = new HashMap();

        try {
            resultRecipes = this.recipesRepository.findOne(recipes.getId());
            if(resultRecipes != null){
                resultMap.put(ConstantsRecipes.OBJECT, resultRecipes);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }

    /**
     * method for get account by email and password
     * @param recipes
     * @return
     */
    @Override
    public HashMap getRecipesByAccounts(Recipes recipes) {
        String messageTemp = "";
        boolean status = false;

        Recipes resultRecipes = null;
        HashMap resultMap = new HashMap();

        try {
            resultRecipes = this.recipesRepository.findAccountByAccounts(recipes);
            if(resultRecipes != null){
                resultMap.put(ConstantsRecipes.OBJECT, resultRecipes);
                status = true;
            }
            else{
                messageTemp = ConstantsRecipes.UNAUTHORIZED_USER;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }

    /**
     * Method save or update in data base
     * @param recipes
     * @return
     */
    @Override
    public HashMap saveOrUpdateRecipes(Recipes recipes) {
        String messageTemp = "";
        boolean status = false;

        HashMap resultMap = new HashMap();

        try {
            this.recipesRepository.save(recipes);

            resultMap.put(ConstantsRecipes.OBJECT, recipes);
            status = true;
        }catch (Exception er){
            messageTemp  = er.getMessage();
            status = false;
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }
}
