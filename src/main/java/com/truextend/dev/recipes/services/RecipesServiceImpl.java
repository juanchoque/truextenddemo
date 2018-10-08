package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.error.ErrorRecipes;
import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.model.Ingredients;
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

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private ErrorRecipes errorRecipes;

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
            status = false;
            //save error in data base
            messageTemp = this.errorRecipes.insert(
                    er,
                    this.getClass(),
                    ConstantsRecipes.PACKAGE_SERVICES,
                    ConstantsRecipes.DEFAULT_USER,
                    ConstantsRecipes.ID_RESERVED);
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

            if(recipes.getName() == null){
                recipes.setName(ConstantsRecipes.EMPTY_VALUE);
            }
            if(recipes.getPreparation() == null){
                recipes.setName(ConstantsRecipes.EMPTY_VALUE);
            }

            listRecipes = this.recipesRepository.findAllRecipes(recipes);
            if(listRecipes != null){
                resultMap.put(ConstantsRecipes.OBJECT, listRecipes);
                status = true;
            }
        }catch (Exception er){
            status = false;
            //save error in data base
            messageTemp = this.errorRecipes.insert(
                    er,
                    this.getClass(),
                    ConstantsRecipes.PACKAGE_SERVICES,
                    ConstantsRecipes.DEFAULT_USER,
                    ConstantsRecipes.ID_RESERVED);
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
            //save error in data base
            messageTemp = this.errorRecipes.insert(
                    er,
                    this.getClass(),
                    ConstantsRecipes.PACKAGE_SERVICES,
                    ConstantsRecipes.DEFAULT_USER,
                    ConstantsRecipes.ID_RESERVED);
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }


    /**
     * method get recipes by accounts
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
            //save error in data base
            messageTemp = this.errorRecipes.insert(
                    er,
                    this.getClass(),
                    ConstantsRecipes.PACKAGE_SERVICES,
                    ConstantsRecipes.DEFAULT_USER,
                    ConstantsRecipes.ID_RESERVED);
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }

    /**
     * Method save or update recipes in data base
     * @param idAcounts
     * @param recipes
     * @return
     */
    @Override
    public HashMap saveOrUpdateRecipes(Integer idAcounts, Recipes recipes) {
        boolean continueHash = false;
        String messageTemp = "";
        boolean status = false;

        Accounts accounts = null;
        HashMap resultMap = new HashMap();
        Recipes resultRecipes = null;

        try {
            accounts = new Accounts();
            accounts.setId(idAcounts);

            //get account required for insert recipes
            resultMap = this.accountsService.getAccountsById(accounts);
            continueHash = (boolean)resultMap.get(ConstantsRecipes.STATUS);
            if(continueHash){
                accounts = (Accounts) resultMap.get(ConstantsRecipes.OBJECT);
                if(accounts != null){//exist accounts
                    if(recipes.getId() != null){
                        resultRecipes = this.recipesRepository.findOne(recipes.getId());
                    }

                    if(resultRecipes != null){
                        //exist recipe delete data before, after that save again with new data ingredients
                        this.ingredientsService.deteleIngredientsByRecipe(recipes);
                    }

                    //set accounts
                    recipes.setAccounts(accounts);

                    //save or update data recipes
                    this.recipesRepository.save(recipes);

                    //save ingredients
                    if(recipes.getListIngredients() != null){
                        for(Ingredients ingredients : recipes.getListIngredients()){
                            ingredients.setRecipes(recipes);
                            this.ingredientsService.saveOrUpdateIngredients(ingredients);
                        }
                    }

                    resultMap.put(ConstantsRecipes.OBJECT, recipes);
                    status = true;
                }
                else{
                    messageTemp  = ConstantsRecipes.MESSAGE_NOT_FOUND_OBJECT;
                    status = false;
                }
            }
            else{
                messageTemp  = ConstantsRecipes.MESSAGE_NOT_FOUND_OBJECT;
                status = false;
            }
        }catch (Exception er){
            status = false;
            //save error in data base
            messageTemp = this.errorRecipes.insert(
                    er,
                    this.getClass(),
                    ConstantsRecipes.PACKAGE_SERVICES,
                    ConstantsRecipes.DEFAULT_USER,
                    ConstantsRecipes.ID_RESERVED);
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }
}
