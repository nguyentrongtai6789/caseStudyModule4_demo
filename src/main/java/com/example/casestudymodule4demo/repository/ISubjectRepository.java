package com.example.casestudymodule4demo.repository;


import com.example.casestudymodule4demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
}
