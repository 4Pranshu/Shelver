package com.example.Shelver.repositories;


import com.example.Shelver.models.Student;
import com.example.Shelver.models.StudentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    @Modifying
    @Transactional
    @Query("update Student s set s.status = ?2 where s.id = ?1")
     void deactivateStudent(Integer studentID, StudentStatus status);


}
