package com.example.casestudymodule4demo.service.model_impl;

import com.example.casestudymodule4demo.model.Student;
import com.example.casestudymodule4demo.repository.modelRepo.IStudentRepository;
import com.example.casestudymodule4demo.service.model_i_service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
