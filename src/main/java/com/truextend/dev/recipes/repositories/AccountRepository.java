package com.truextend.dev.recipes.repositories;

import com.truextend.dev.recipes.model.Accounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Accounts, Integer> {
    @Query("FROM Accounts a where a.email = :#{#accounts.email} and a.password = :#{#accounts.password}")
    Accounts findAccountByByEmailAndPass(@Param("accounts") Accounts accounts);

    @Query("FROM Accounts a where a.email = :#{#accounts.email}")
    Accounts findAccountByByEmail(@Param("accounts")Accounts verifyAccount);
}
