package com.example.Shelver.controllers;
import com.example.Shelver.dtos.CreateStudentRequest;
import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.dtos.UpdateStudentRequest;
import com.example.Shelver.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Integer createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        return studentService.createStudent(createStudentRequest);
    }


    @GetMapping("/{studentId}")
    public GetStudentDetailsResponse getStudentDetails(@PathVariable("studentId") int studentId) {
        return studentService.getStudentDetails(studentId);
    }

    // Patch api is used when frontend is sending some data not entire..............
    @PatchMapping("/{studentId}")
    public GetStudentDetailsResponse updateStudentDetails(@PathVariable("studentId") Integer studentId ,@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        return studentService.updateStudentDetails(studentId,updateStudentRequest);
    }

    @DeleteMapping("/{studentId}")
    public GetStudentDetailsResponse deactivateStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deactivateStudent(studentId);
    }


}
