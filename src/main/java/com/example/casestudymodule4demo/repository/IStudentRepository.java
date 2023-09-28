package com.example.casestudymodule4demo.repository;


import com.example.casestudymodule4demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select * from student where name like %?%", nativeQuery = true)
    List<Student> searchByName(String name);
}
