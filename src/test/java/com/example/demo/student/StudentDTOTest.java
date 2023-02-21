package com.example.demo.student;

import com.example.demo.dto.StudentDTO;
import com.example.demo.dtoconverter.StudentDTOConverter;
import com.example.demo.model.student.Student;
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
    public void ShouldCheckIfCorrectlyConvertsModelToDTO()
    {
        Student student = new Student("Carlos", LocalDate.of(1993, Month.SEPTEMBER, 25), "mail@gmail.com");
        StudentDTO studentDTO = studentDTOConverter.convertStudentToDTO(student);

        assertEquals(studentDTO.getName(), "Carlos");
        assertEquals(studentDTO.getDateOfBirth(), LocalDate.of(1993, Month.SEPTEMBER, 25));
        assertEquals(studentDTO.getAge(), 29);
    }
}
