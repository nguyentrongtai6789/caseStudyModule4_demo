package com.example.casestudymodule4demo.service.model_impl;

import com.example.casestudymodule4demo.model.Status;
import com.example.casestudymodule4demo.repository.modelRepo.IStatusRepository;
import com.example.casestudymodule4demo.service.model_i_service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository statusRepository;
    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Page<Status> findAll(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);}

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }
}
