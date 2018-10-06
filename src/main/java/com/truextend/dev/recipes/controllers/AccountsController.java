package com.truextend.dev.recipes.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.model.MessageError;
import com.truextend.dev.recipes.services.AccountsService;
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
@RequestMapping("accounts")
@Api(value = ConstantsRecipes.VALUE_ACCOUNTS_CONTROLLER, description = ConstantsRecipes.DESC_ACCOUNTS_CONTROLLER)
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    /**
     * List all accounts
     * @param accounts
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_LIST_ACCOUNTS)
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> listAccounts(@RequestBody Accounts accounts) {
        boolean continueHash = false;
        String messageHash = "";
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        List<Accounts> listAccounts = new ArrayList<Accounts>();
        RecipesUtil recipesUtil = new RecipesUtil();
        ResponseEntity<Object> response = null;
        HashMap resultHashMap = null;

        try {
            resultHashMap = this.accountsService.getAllAccounts(accounts);
            continueHash = (boolean) resultHashMap.get(ConstantsRecipes.STATUS);
            if (continueHash) {
                listAccounts = (List<Accounts>) resultHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(listAccounts);
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
     * create a nuew account
     * @param accounts
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_ADD_ACCOUNTS)
    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> addAccounts(@RequestBody Accounts accounts) {
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
        Accounts resultAccounts = new Accounts();
        ResponseEntity<Object> response = null;

        try {
            resHashMap = this.accountsService.saveOrUpdateAccounts(accounts);
            continueHash = (boolean)resHashMap.get(ConstantsRecipes.STATUS);
            if(continueHash){
                resultAccounts = (Accounts) resHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(resultAccounts, Accounts.class);
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
     * create a nuew account
     * @param accounts
     * @return
     */
    @ApiOperation(value = ConstantsRecipes.DESC_GET_ACCOUNTS)
    @ResponseBody
    @RequestMapping(value="/get", method= RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> getAccounts(@RequestBody Accounts accounts) {
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
        Accounts resultAccounts = new Accounts();
        ResponseEntity<Object> response = null;

        try {
            resHashMap = this.accountsService.getAccountsByEmailAndPass(accounts);
            continueHash = (boolean)resHashMap.get(ConstantsRecipes.STATUS);
            if(continueHash){
                resultAccounts = (Accounts) resHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(resultAccounts, Accounts.class);
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



    @ApiOperation(value = ConstantsRecipes.DESC_DELETE_ACCOUNTS)
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> deleteAccounts(@RequestBody Accounts accounts) {
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
        Accounts resultAccounts = new Accounts();
        ResponseEntity<Object> response = null;

        try {
            resHashMap = this.accountsService.deteleAccounts(accounts);
            continueHash = (boolean) resHashMap.get(ConstantsRecipes.STATUS);
            if (continueHash) {
                resultAccounts = (Accounts) resHashMap.get(ConstantsRecipes.OBJECT);
                resultJson = gson.toJson(resultAccounts, Accounts.class);
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
