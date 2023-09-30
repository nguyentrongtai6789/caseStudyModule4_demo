package com.example.casestudymodule4demo.repository.accountRepo;

import com.example.casestudymodule4demo.model.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
