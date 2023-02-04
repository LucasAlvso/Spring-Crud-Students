package com.example.demo.student;

import org.springframework.stereotype.Service;

@Service
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
