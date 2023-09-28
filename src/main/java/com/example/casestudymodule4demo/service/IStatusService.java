package com.example.casestudymodule4demo.service;



import com.example.casestudymodule4demo.model.Status;

import java.util.List;

public interface IStatusService extends IGenerateService<Status> {
    public List<Status> findAll();
}
