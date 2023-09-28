package com.example.casestudymodule4demo.service;



import com.example.casestudymodule4demo.model.Subject;

import java.util.List;

public interface ISubjectService extends IGenerateService<Subject>{
    List<Subject> findAll();
}
