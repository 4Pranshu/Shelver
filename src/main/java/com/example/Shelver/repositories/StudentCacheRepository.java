package com.example.Shelver.repositories;

import com.example.Shelver.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class StudentCacheRepository {

    private static final String STUDENT_KEY_PREFIX = "stu::";

    private static final Long STUDENT_KEY_EXPIRY = 3600L;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public void add(Student student){
        String key =  this.getKey(student.getId());
        this.redisTemplate.opsForValue().set(key,student, STUDENT_KEY_EXPIRY,TimeUnit.SECONDS);
    }

    public Student get(Integer studentId){
        String key =  this.getKey(studentId);
        return (Student) this.redisTemplate.opsForValue().get(key);
    }
    
    public String getKey(Integer studentId){
        return STUDENT_KEY_PREFIX+studentId;
    }


}
