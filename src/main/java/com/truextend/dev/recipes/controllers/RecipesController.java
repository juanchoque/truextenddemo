package com.truextend.dev.recipes.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truextend.dev.recipes.model.Recipes;
import com.truextend.dev.recipes.model.MessageError;
import com.truextend.dev.recipes.model.SecurityDataObject;
import com.truextend.dev.recipes.security.JwtValidator;
import com.truextend.dev.recipes.services.RecipesService;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import com.truextend.dev.recipes.util.RecipesUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("recipes")
@Api(value = ConstantsRecipes.VALUE_RECIPES_CONTROLLER, description = ConstantsRecipes.DESC_RECIPES_CONTROLLER)
public class RecipesController {
    @Autowired
    private RecipesService recipesService;

    @Autowired
    private JwtValidator jwtValidator;

    /**
     * List all recipes
     * @param recipes
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_LIST_RECIPES)
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> listRecipes(@RequestBody Recipes recipes) {
        boolean continueHash = false;
        String messageHash = "";
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        List<Recipes> listRecipes = new ArrayList<Recipes>();
        RecipesUtil recipesUtil = new RecipesUtil();
        ResponseEntity<Object> response = null;
        HashMap resultHashMap = null;

        try {
            resultHashMap = this.recipesService.getAllRecipes(recipes);
            continueHash = (boolean) resultHashMap.get(ConstantsRecipes.STATUS);
            if (continueHash) {
                listRecipes = (List<Recipes>) resultHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(listRecipes);
            }
            else {
                messageHash = (String)resultHashMap.get(ConstantsRecipes.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
                resultJson = gson.toJson(messages);
            }

        } catch (Exception ex) {
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
            resultJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(resultJson, httpStatus);

        return response;
    }

    /**
     * create a new account
     * @param recipes
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_ADD_RECIPES)
    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> addRecipes(@RequestHeader(value="Authorization") String token, @RequestBody Recipes recipes) {
        boolean continueHash = false;
        Integer idAccounts = 0;
        String messageHash = "";
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        RecipesUtil recipesUtil = new RecipesUtil();
        HashMap resHashMap = null;
        Recipes resultRecipes = new Recipes();
        ResponseEntity<Object> response = null;
        SecurityDataObject securityDataObject = null;

        try {
            //get data from token, get idAccount
            securityDataObject = this.jwtValidator.validate(token);
            if(securityDataObject != null) {//user authorized */
                idAccounts = securityDataObject.getIdAccount();

                resHashMap = this.recipesService.saveOrUpdateRecipes(idAccounts, recipes);
                continueHash = (boolean)resHashMap.get(ConstantsRecipes.STATUS);
                if(continueHash){
                    resultRecipes = (Recipes) resHashMap.get(ConstantsRecipes.OBJECT);
                    resultJson = gson.toJson(resultRecipes, Recipes.class);
                }
                else{
                    messageHash = (String)resHashMap.get(ConstantsRecipes.MESSAGE);
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                    MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
                    resultJson = gson.toJson(messages);
                }
            }
            else{
                messageHash = ConstantsRecipes.UNAUTHORIZED_USER;
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
                resultJson = gson.toJson(messages);
            }
        }catch (Exception ex){
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
            resultJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(resultJson, httpStatus);

        return response;
    }

    /**
     * create a nuew account
     * @param recipes
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_GET_RECIPES)
    @ResponseBody
    @RequestMapping(value="/get", method= RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> getRecipes(@RequestBody Recipes recipes) {
        boolean continueHash = false;
        String messageHash = "";
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        RecipesUtil recipesUtil = new RecipesUtil();
        HashMap resHashMap = null;
        Recipes resultRecipes = new Recipes();
        ResponseEntity<Object> response = null;

        try {
            resHashMap = this.recipesService.getRecipesByAccounts(recipes);
            continueHash = (boolean)resHashMap.get(ConstantsRecipes.STATUS);
            if(continueHash){
                resultRecipes = (Recipes) resHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(resultRecipes, Recipes.class);
            }
            else{
                messageHash = (String)resHashMap.get(ConstantsRecipes.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
                resultJson = gson.toJson(messages);
            }
        }catch (Exception ex){
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
            resultJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(resultJson, httpStatus);

        return response;
    }

    /**
     * method delete user accont only logical deleting (change state to 0)
     * @param recipes
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_DELETE_RECIPES)
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> deleteRecipes(@RequestBody Recipes recipes) {
        boolean continueHash = false;
        String messageHash = "";
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        RecipesUtil recipesUtil = new RecipesUtil();
        HashMap resHashMap = null;
        Recipes resultRecipes = new Recipes();
        ResponseEntity<Object> response = null;

        try {
            resHashMap = this.recipesService.deteleRecipes(recipes);
            continueHash = (boolean) resHashMap.get(ConstantsRecipes.STATUS);
            if (continueHash) {
                resultRecipes = (Recipes) resHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(resultRecipes, Recipes.class);
            }
            else {
                messageHash = (String)resHashMap.get(ConstantsRecipes.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
                resultJson = gson.toJson(messages);
            }
        } catch (Exception ex) {
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = recipesUtil.getFormatMessage(messageHash, httpStatus);
            resultJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(resultJson, httpStatus);

        return response;
    }

}
