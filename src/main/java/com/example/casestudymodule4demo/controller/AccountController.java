package com.example.casestudymodule4demo.controller;

import com.example.casestudymodule4demo.jwt.service.JwtResponse;
import com.example.casestudymodule4demo.jwt.service.JwtService;
import com.example.casestudymodule4demo.model.Student;
import com.example.casestudymodule4demo.model.account.Account;
import com.example.casestudymodule4demo.model.account.DTO.AccountDTO;
import com.example.casestudymodule4demo.service.account_impl.AccountService;
import com.example.casestudymodule4demo.service.model_i_service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAllAccount() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getName(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account accountInfo = accountService.findByAccountName(account.getName());
        return ResponseEntity.ok(new JwtResponse(accountInfo.getId(), jwt, accountInfo.getName(),
                accountInfo.getName(), userDetails.getAuthorities()));
    }

}
