package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDTO;
import com.example.demo.student.StudentDTOConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class StudentDTOTest
{
    @Autowired
    private StudentDTOConverter studentDTOConverter;


    @Test
    public void dtoConversionTest()
    {
        Student student = new Student("Carlos", LocalDate.of(1993, Month.SEPTEMBER, 25), "mail@gmail.com");
        StudentDTO studentDTO = studentDTOConverter.convertStudentToDTO(student);

        assertEquals(studentDTO.getName(), "Carlos");
        assertEquals(studentDTO.getDateOfBirth(), LocalDate.of(1993, Month.SEPTEMBER, 25));
        assertEquals(studentDTO.getAge(), 29);
    }
}
