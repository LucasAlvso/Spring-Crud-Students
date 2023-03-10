package com.example.demo.student;

import com.example.demo.model.student.Student;
import com.example.demo.repository.student.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest
{

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void ShouldCheckIfModelIsInValid()
    {
        Student student = new Student("", null, null);

        assertFalse(studentService.studentModelIsValid(student));
    }

    @Test
    void ShouldCheckIfModelIsValid()
    {
        Student student = new Student("Lucas", LocalDate.now(), "email@gmail.com");

        assertTrue(studentService.studentModelIsValid(student));
    }

    @Test
    void shouldFindStudentById()
    {
        Student student = new Student(90L, "Carlos", LocalDate.of(1999, 8, 25), "carlos@gmail.com");

        when(studentRepository.findById(90L)).thenReturn(Optional.of(student));

        Student result = studentService.findStudentById(90L).get();

        assertEquals(student, result);
    }

    @Test
    void shouldSaveStudent()
    {
        Student student = new Student(90L, "Carlos", LocalDate.of(1999, 8, 25), "carlos@gmail.com");

        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentRepository.save(student);

        assertEquals(student, result);
    }

    @Test
    void shouldDeleteStudentById()
    {
        Long studentId = 123L;
        when(studentRepository.existsById(anyLong())).thenReturn(true);

        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    void shouldThrowExceptionTryingToDeleteStudent()
    {
        assertThrows(IllegalStateException.class, () -> studentService.deleteStudent(999999999999L));
    }


}
