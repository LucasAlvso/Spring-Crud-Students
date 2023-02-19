package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StudentDTOConverter
{
    public StudentDTO convertStudentToDTO(Student student)
    {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAge(student.getAge());
        studentDTO.setName(student.getName());
        studentDTO.setDateOfBirth(student.getDateOfBirth());

        return studentDTO;
    }
}
