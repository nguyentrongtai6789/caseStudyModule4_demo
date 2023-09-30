package com.example.casestudymodule4demo.service.account_impl;

import com.example.casestudymodule4demo.model.account.Account;
import com.example.casestudymodule4demo.model.account.AccountPrinciple;
import com.example.casestudymodule4demo.model.account.DTO.AccountDTO;
import com.example.casestudymodule4demo.repository.accountRepo.IAccountRepository;
import com.example.casestudymodule4demo.service.account_i_service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<AccountDTO> findAll() {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accountRepository.findAll()) {
            accountDTOS.add(toDTO(account));
        }
        return accountDTOS; // trả ra 1 list account DTO để thao tác gì đó mà ko thao tác trực tiếp với account
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    public AccountDTO toDTO(Account account) {
        return new AccountDTO(account.getId(), account.getName(), account.getRoles());
    }

    @Override
    public AccountDTO findById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            return toDTO(accountOptional.get());
        }
        return null;
    }

    @Override
    public boolean add(Account account) {
//        String passwordEncode = passwordEncoder.encode(account.getPassword());
//        account.setPassword(passwordEncode);
        accountRepository.save(account);
        return true;
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String name) {
        Account account = accountRepository.findByName(name);
        if (account != null) {
            return AccountPrinciple.build(account); // tạo ra một đối tượng accountPrinciple từ account
        }
        return null;
    }
}
