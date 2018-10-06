package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Ingredients;
import com.truextend.dev.recipes.repositories.IngredientsRepository;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    /**
     * method that delete a ingredients in data base
     * @param ingredients
     * @return
     */
    @Override
    public HashMap deteleIngredients(Ingredients ingredients) {
        String messageTemp = "";
        boolean status = false;

        Ingredients editIngredients = null;
        HashMap resultMap = new HashMap();

        try {
            editIngredients = this.ingredientsRepository.findOne(ingredients.getId());
            if(editIngredients != null){
                //logical deleting only
                editIngredients.setState(ConstantsRecipes.STATE_INACTIVE);
                this.ingredientsRepository.delete(editIngredients);
                resultMap.put(ConstantsRecipes.OBJECT, editIngredients);
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
     * Method get list ingredients from data base
     * @param ingredients
     * @return
     */
    @Override
    public HashMap getAllIngredients(Ingredients ingredients) {
        String messageTemp = "";
        boolean status = false;

        List<Ingredients> listIngredients = new ArrayList<>();
        HashMap resultMap = new HashMap();

        try {
            if(ingredients == null){
                ingredients = new Ingredients();
            }
            ingredients.setState(ConstantsRecipes.STATE_ACTIVE);

            this.ingredientsRepository.findAll().forEach(listIngredients::add);
            if(listIngredients != null){
                resultMap.put(ConstantsRecipes.OBJECT, listIngredients);
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
     * @param ingredients
     * @return
     */
    @Override
    public HashMap getIngredientsById(Ingredients ingredients) {
        String messageTemp = "";
        boolean status = false;

        Ingredients resultIngredients = null;
        HashMap resultMap = new HashMap();

        try {
            resultIngredients = this.ingredientsRepository.findOne(ingredients.getId());
            if(resultIngredients != null){
                resultMap.put(ConstantsRecipes.OBJECT, resultIngredients);
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
     * Method save or update in data base
     * @param ingredients
     * @return
     */
    @Override
    public HashMap saveOrUpdateIngredients(Ingredients ingredients) {
        String messageTemp = "";
        boolean status = false;

        HashMap resultMap = new HashMap();

        try {
            this.ingredientsRepository.save(ingredients);

            resultMap.put(ConstantsRecipes.OBJECT, ingredients);
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
