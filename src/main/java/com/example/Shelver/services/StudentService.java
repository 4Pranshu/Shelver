package com.example.Shelver.services;

import com.example.Shelver.dtos.CreateStudentRequest;
import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.dtos.UpdateStudentRequest;
import com.example.Shelver.models.*;
import com.example.Shelver.repositories.StudentCacheRepository;
import com.example.Shelver.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCacheRepository studentCacheRepository;


    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    public GetStudentDetailsResponse getStudentDetails(Integer studentId) {

        Student student = this.studentCacheRepository.get(studentId);

        if (student != null) {
            return GetStudentDetailsResponse.builder()
                    .student(student)
                    .build();
        }

        student = studentRepository.findById(studentId).orElse(null);

        this.studentCacheRepository.add(student);
        return GetStudentDetailsResponse.builder()
                .student(student)
                .build();
    }

    public Integer createStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        User user = userService.create(student.getUser(), Authority.STUDENT);
        student.setUser(user);
        student = studentRepository.save(student);
        return student.getId();
    }

    public GetStudentDetailsResponse updateStudentDetails(Integer studentId, UpdateStudentRequest updateStudentRequest) {
        Student student = updateStudentRequest.to();
        GetStudentDetailsResponse getStudentDetailsResponse = getStudentDetails(studentId);
        Student savedStudent = getStudentDetailsResponse.getStudent();
        Student target = this.deepMerge(student, savedStudent);
        studentRepository.save(target);

        getStudentDetailsResponse.setStudent(target);
        return getStudentDetailsResponse;
    }


    private Student deepMerge(Student incoming, Student saved) {
        JSONObject incomingStudent = mapper.convertValue(incoming, JSONObject.class);
        JSONObject savedStudent = mapper.convertValue(saved, JSONObject.class);


        for (Object o : incomingStudent.keySet()) {
            String key = (String) o;
            if (incomingStudent.get(key) != null) {
                savedStudent.put(key, incomingStudent.get(key));
            }
        }
        return mapper.convertValue(savedStudent, Student.class);
    }

    public GetStudentDetailsResponse deactivateStudent(Integer studentId) {
        studentRepository.deactivateStudent(studentId, StudentStatus.INACTIVE);
        GetStudentDetailsResponse getStudentDetailsResponse = getStudentDetails(studentId);
        return getStudentDetailsResponse;
    }
}
