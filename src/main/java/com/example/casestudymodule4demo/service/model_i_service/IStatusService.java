package com.example.casestudymodule4demo.service.model_i_service;



import com.example.casestudymodule4demo.model.Status;
import com.example.casestudymodule4demo.service.IGenerateService;

import java.util.List;

public interface IStatusService extends IGenerateService<Status> {
    List<Status> findAll();
}
