package com.example.casestudymodule4demo.controller;

import com.example.casestudymodule4demo.model.Status;
import com.example.casestudymodule4demo.model.Student;
import com.example.casestudymodule4demo.service.model_i_service.IStatusService;
import com.example.casestudymodule4demo.service.model_i_service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IStatusService statusService;
    @Value("${upload.path}")
    private String upload;

    @GetMapping("/pageStudent")
    public ResponseEntity<Page<Student>> getPageStudent(Pageable pageable) {
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/listStatus")
    public ResponseEntity<List<Status>> getListStatus() {
        return new ResponseEntity<>(statusService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findStudentById/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<?> saveStudent(@RequestPart Student student,
                                         @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        if (Objects.equals(student.getDateOfBirth(), null)) {
            student.setDateOfBirth(LocalDate.parse("2005-01-01"));
        }
        saveImage(multipartFile, student);
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // lưu ảnh và set urlImg cho student:
    private void saveImage(MultipartFile file, Student student) {
        // nếu chọn file thì tiến hành lưu ảnh
        if (file.getSize() > 0) {
            String name = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(upload + name));
            } catch (Exception e) {
                e.printStackTrace();
            }
            student.setUrl_img(name);
        }
        // nếu không chọn file và id student = null thì đặt ảnh mặc định:
        if (file.getSize() == 0 && Objects.equals(student.getId(), null)) {
            student.setUrl_img("download.jpg");
        }
        // nếu không chọn file và id student khác null thì đặt ảnh cũ:
        if (file.getSize() == 0 && !Objects.equals(student.getId(), null)) {
            Student student1 = studentService.findById(student.getId()).get();
            student.setUrl_img(student1.getUrl_img());
        }
    }
}
