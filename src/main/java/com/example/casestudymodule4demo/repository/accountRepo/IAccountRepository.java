package com.example.casestudymodule4demo.repository.accountRepo;

import com.example.casestudymodule4demo.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String name);
}
