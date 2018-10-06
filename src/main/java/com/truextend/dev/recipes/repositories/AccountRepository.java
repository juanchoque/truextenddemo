package com.truextend.dev.recipes.repositories;

import com.truextend.dev.recipes.model.Accounts;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepository extends CrudRepository<Accounts, Integer> {
    @Query("FROM Accounts a where a.email = :#{#accounts.email} and a.password = :#{#accounts.password}")
    Accounts findAccountByByEmailAndPass(@Param("accounts") Accounts accounts);

    @Query("FROM Accounts a where a.email = :#{#accounts.email}")
    Accounts findAccountByByEmail(@Param("accounts")Accounts verifyAccount);

    @Query("FROM Accounts a where a.state = :#{#accounts.state}")
    List<Accounts> findAllAccount(@Param("accounts")Accounts accounts);

    @Modifying
    @Query("UPDATE Accounts a SET a.state = :#{#accounts.state} where a.id = :#{#accounts.id}")
    @Transactional
    void deleteAccounts(@Param("accounts")Accounts accounts);
}
