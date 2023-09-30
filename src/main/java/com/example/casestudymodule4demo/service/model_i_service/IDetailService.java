package com.example.casestudymodule4demo.service.model_i_service;



import com.example.casestudymodule4demo.model.DetailStudentAndSubject;
import com.example.casestudymodule4demo.model.Student;
import com.example.casestudymodule4demo.service.IGenerateService;

import java.util.List;

public interface IDetailService extends IGenerateService<DetailStudentAndSubject> {
    void deleteAllByStudent_Id(Long id);
    List<DetailStudentAndSubject> findAllByStudent(Student student);
    List<DetailStudentAndSubject> selectAllByStudent_Id(Long id);
    List<DetailStudentAndSubject> findAllList();
}
