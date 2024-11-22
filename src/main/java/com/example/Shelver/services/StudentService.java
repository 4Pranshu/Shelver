package com.example.Shelver.services;
import com.example.Shelver.dtos.CreateStudentRequest;
import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.dtos.UpdateStudentRequest;
import com.example.Shelver.models.Book;
import com.example.Shelver.models.Student;
import com.example.Shelver.models.StudentStatus;
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
    private BookService bookService;

    private ObjectMapper mapper = new ObjectMapper();
    public GetStudentDetailsResponse getStudentDetails(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
//        List<Book> bookList = bookService.getBookDetailsBuStudentId(studentId);
        return GetStudentDetailsResponse.builder()
//                .bookList(bookList)
                .student(student)
                .build();
    }

    public Integer createStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        student= studentRepository.save(student);
        return student.getId();
    }

    public GetStudentDetailsResponse updateStudentDetails(Integer studentId, UpdateStudentRequest updateStudentRequest) {
        Student student = updateStudentRequest.to();
        GetStudentDetailsResponse getStudentDetailsResponse = getStudentDetails(studentId);
        Student savedStudent = getStudentDetailsResponse.getStudent();
        Student target = this.deepMerge(student,savedStudent);
        studentRepository.save(target);

        getStudentDetailsResponse.setStudent(target);
        return getStudentDetailsResponse;
    }


    private Student deepMerge(Student incoming,Student saved) {
        JSONObject incomingStudent = mapper.convertValue(incoming, JSONObject.class);
        JSONObject savedStudent = mapper.convertValue(saved, JSONObject.class);


        for (Object o : incomingStudent.keySet()) {
            String key = (String) o;
            if (incomingStudent.get(key) != null) {
                savedStudent.put(key, incomingStudent.get(key));
            }
        }
        return mapper.convertValue(savedStudent,Student.class);
    }

    public GetStudentDetailsResponse deactivateStudent(Integer studentId) {
        studentRepository.deactivateStudent(studentId, StudentStatus.INACTIVE);
        GetStudentDetailsResponse getStudentDetailsResponse = getStudentDetails(studentId);
        return  getStudentDetailsResponse;
    }
}
