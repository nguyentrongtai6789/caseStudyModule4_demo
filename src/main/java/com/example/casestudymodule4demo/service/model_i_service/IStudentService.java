package com.example.casestudymodule4demo.service.model_i_service;



import com.example.casestudymodule4demo.model.Student;
import com.example.casestudymodule4demo.service.IGenerateService;

import java.util.List;

public interface IStudentService extends IGenerateService<Student> {
    List<Student> listStudent();
}
