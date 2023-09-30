package com.example.casestudymodule4demo.service.account_i_service;

import com.example.casestudymodule4demo.model.account.Account;
import com.example.casestudymodule4demo.model.account.DTO.AccountDTO;
import com.example.casestudymodule4demo.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();
    AccountDTO findById(Long id);
    Account findByAccountName(String name);
    boolean add(Account account);
    void delete(Long id);
    AccountDTO toDTO(Account account);
}
