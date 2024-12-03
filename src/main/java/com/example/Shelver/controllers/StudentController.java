
package com.example.Shelver.controllers;
import com.example.Shelver.dtos.CreateStudentRequest;
import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.dtos.UpdateStudentRequest;
import com.example.Shelver.models.Authority;
import com.example.Shelver.models.Student;
import com.example.Shelver.models.User;
import com.example.Shelver.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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


    // for getting student details by admin (Authority of Admin)
    @GetMapping("admin/{studentId}")
    public GetStudentDetailsResponse getStudentDetailsForAdmin(@PathVariable("studentId") int studentId)  {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();


        return studentService.getStudentDetails(studentId);
    }

    // for getting his own student details by student (Authority of Student)
    @GetMapping("")
    public GetStudentDetailsResponse getStudentDetails(
//            @PathVariable("studentId") int studentId
    ) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Student student = user.getStudent();

        Integer studentId= null;
        if(student!=null){
            studentId = student.getId();
        }
        else{
            throw new Exception("User is not a student");
        }
        return studentService.getStudentDetails(studentId);
    }

    // Patch api is used when frontend is sending some data not entire..............
    @PatchMapping("")
    public GetStudentDetailsResponse updateStudentDetails(
//            @PathVariable("studentId") Integer studentId ,
            @Valid @RequestBody UpdateStudentRequest updateStudentRequest) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Student student = user.getStudent();

        Integer studentId= null;
        if(student!=null){
            studentId = student.getId();
        }
        else{
            throw new Exception("User is not a student");
        }
        return studentService.updateStudentDetails(studentId,updateStudentRequest);
    }

    @DeleteMapping("")
    public GetStudentDetailsResponse deactivateStudent(
//            @PathVariable("studentId") Integer studentId
    ) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Student student = user.getStudent();

        Integer studentId= null;
        if(student!=null){
            studentId = student.getId();
        }
        else{
            throw new Exception("User is not a student");
        }
        return studentService.deactivateStudent(studentId);
    }


}
