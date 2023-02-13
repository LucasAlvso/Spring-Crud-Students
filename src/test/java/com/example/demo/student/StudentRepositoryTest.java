package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StudentRepositoryTest
{

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void ShouldCheckIfStudentIsFoundByEmail()
    {
        Student student = new Student("Romeu", LocalDate.of(1997, 7, 25), "romeu@gmail.com");

        studentRepository.save(student);

        Student studentByEmail = studentRepository.findStudentByEmail("romeu@gmail.com").get();

        assertNotNull(studentByEmail);
    }
}
