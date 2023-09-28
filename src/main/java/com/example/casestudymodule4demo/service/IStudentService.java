package com.example.casestudymodule4demo.service;



import com.example.casestudymodule4demo.model.Student;

import java.util.List;

public interface IStudentService extends IGenerateService<Student>{
    List<Student> listAllStudent();
}
