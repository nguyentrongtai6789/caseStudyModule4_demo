package com.example.casestudymodule4demo.service.model_i_service;



import com.example.casestudymodule4demo.model.Subject;
import com.example.casestudymodule4demo.service.IGenerateService;

import java.util.List;

public interface ISubjectService extends IGenerateService<Subject> {
    List<Subject> findAll();
}
