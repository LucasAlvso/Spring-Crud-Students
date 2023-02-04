package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest
{

    @Autowired
    private StudentService studentService;

    @Test
    public void studentModelIsValidTest()
    {
        Student student = new Student("", null, null);

        assertFalse(studentService.studentModelIsValid(student));
    }
}
