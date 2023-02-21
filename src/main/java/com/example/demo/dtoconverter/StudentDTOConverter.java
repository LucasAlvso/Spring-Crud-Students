package com.example.demo.dtoconverter;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.student.Student;
import org.springframework.stereotype.Component;

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
