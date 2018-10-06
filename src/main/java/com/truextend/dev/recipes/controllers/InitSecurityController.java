package com.truextend.dev.recipes.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.model.MessageError;
import com.truextend.dev.recipes.model.SecurityDataObject;
import com.truextend.dev.recipes.security.JwtGenerator;
import com.truextend.dev.recipes.services.AccountsService;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import com.truextend.dev.recipes.util.RecipesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("token")
public class InitSecurityController {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private JwtGenerator jwtGenerator;

    /**
     * create a new account
     * @param accounts
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/createaccount", method= RequestMethod.POST, produces = ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> createAccount(@RequestBody Accounts accounts) {
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
     * method validate user accoounts and get JWT security
     * @param accounts
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/verify", method= RequestMethod.POST, produces=ConstantsRecipes.APP_JSON)
    public ResponseEntity<Object> verifyAccount(@RequestBody Accounts accounts) {
        boolean continueHash = false;
        String resultJson = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantsRecipes.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();
        HttpStatus httpStatus = HttpStatus.OK;
        RecipesUtil recipesUtil = new RecipesUtil();
        MessageError messageError = null;
        HashMap resHashMap = null;
        ResponseEntity<Object> response = null;
        Accounts resultAcconts = null;
        SecurityDataObject securityDataObject = null;

        try {
            //get account
            resHashMap = this.accountsService.getAccountsByEmailAndPass(accounts);
            continueHash = (boolean)resHashMap.get(ConstantsRecipes.STATUS);
            if(continueHash){
                resultAcconts = (Accounts) resHashMap.get(ConstantsRecipes.OBJECT);

                if(resultAcconts != null){
                    securityDataObject = new SecurityDataObject();
                    securityDataObject.setIdAccount(resultAcconts.getId());

                    //get token limited by data time
                    String token = jwtGenerator.generate(securityDataObject);

                    resultAcconts.setToken(token);
                    resultJson = gson.toJson(resultAcconts, Accounts.class);
                }
                else{
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                    messageError = new MessageError(ConstantsRecipes.UNAUTHORIZED_USER,
                            HttpStatus.INTERNAL_SERVER_ERROR.value());
                    resultJson = gson.toJson(messageError, MessageError.class);
                }
            }
            else{
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                messageError = new MessageError((String)resHashMap.get(ConstantsRecipes.MESSAGE),
                        HttpStatus.INTERNAL_SERVER_ERROR.value());
                resultJson = gson.toJson(messageError, MessageError.class);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = recipesUtil.getFormatMessage(ex.getMessage(), httpStatus);
            resultJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(resultJson, httpStatus);

        return response;

    }

}
