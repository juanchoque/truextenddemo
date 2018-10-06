package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.repositories.AccountRepository;
import com.truextend.dev.recipes.util.ConstantsRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * method that delete a accounts in data base
     * @param accounts
     * @return
     */
    @Override
    public HashMap deteleAccounts(Accounts accounts) {
        String messageTemp = "";
        boolean status = false;

        HashMap resultMap = new HashMap();

        try {
            this.accountRepository.delete(accounts);
            resultMap.put(ConstantsRecipes.OBJECT, accounts);
            status = true;
        }catch (Exception er){
            messageTemp = er.getMessage();
            status = false;
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }

    /**
     * Method get list accounts from data base
     * @param accounts
     * @return
     */
    @Override
    public HashMap getAllAccounts(Accounts accounts) {
        String messageTemp = "";
        boolean status = false;

        List<Accounts> listAccounts = new ArrayList<>();
        HashMap resultMap = new HashMap();

        try {
            this.accountRepository.findAll().forEach(listAccounts::add);
            if(listAccounts != null){
                resultMap.put(ConstantsRecipes.OBJECT, listAccounts);
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
     * @param accounts
     * @return
     */
    @Override
    public HashMap getAccountsById(Accounts accounts) {
        String messageTemp = "";
        boolean status = false;

        Accounts resultAccounts = null;
        HashMap resultMap = new HashMap();

        try {
            resultAccounts = this.accountRepository.findOne(accounts.getId());
            if(resultAccounts != null){
                resultMap.put(ConstantsRecipes.OBJECT, resultAccounts);
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
     * @param accounts
     * @return
     */
    @Override
    public HashMap getAccountsByEmailAndPass(Accounts accounts) {
        String messageTemp = "";
        boolean status = false;

        Accounts resultAccounts = null;
        HashMap resultMap = new HashMap();

        try {
            resultAccounts = this.accountRepository.findAccountByByEmailAndPass(accounts);
            if(resultAccounts != null){
                resultMap.put(ConstantsRecipes.OBJECT, resultAccounts);
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
     * @param accounts
     * @return
     */
    @Override
    public HashMap saveOrUpdateAccounts(Accounts accounts) {
        String messageTemp = "";
        boolean status = false;

        HashMap resultMap = new HashMap();
        Accounts verifyAccount = null;

        try {
            verifyAccount = this.accountRepository.findAccountByByEmail(accounts);
            if(verifyAccount == null){
                this.accountRepository.save(accounts);

                resultMap.put(ConstantsRecipes.OBJECT, accounts);
                status = true;
            }
            else{
                messageTemp = ConstantsRecipes.MESSAGE_EXIST_USER_ACCOUNT;
                status = false;
            }

        }catch (Exception er){
            messageTemp  = er.getMessage();
            status = false;
        }

        resultMap.put(ConstantsRecipes.MESSAGE, messageTemp);
        resultMap.put(ConstantsRecipes.STATUS, status);

        return resultMap;
    }
}
