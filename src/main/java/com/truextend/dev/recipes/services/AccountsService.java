package com.truextend.dev.recipes.services;

import com.truextend.dev.recipes.model.Accounts;

import java.util.HashMap;

public interface AccountsService {
    HashMap deteleAccounts(Accounts accounts);

    HashMap getAllAccounts(Accounts accounts);

    HashMap getAccountsById(Accounts accounts);

    HashMap getAccountsByEmailAndPass(Accounts accounts);

    HashMap saveOrUpdateAccounts(Accounts accounts);

}
