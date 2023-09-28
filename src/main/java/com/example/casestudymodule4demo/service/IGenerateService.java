package com.example.casestudymodule4demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService<E> {

    Page<E> findAll(Pageable pageable);

    Optional<E> findById(Long id);

    void save(E e);

    void deleteById(Long id);
}
