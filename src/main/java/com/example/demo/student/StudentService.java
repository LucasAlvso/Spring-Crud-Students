package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student)
    {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId)
    {
        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists)
        {
            throw new IllegalStateException("Student with id " + studentId + " does not exist.");
        }

        studentRepository.deleteById(studentId);
    }

    //Transactional will take on-memory changes and apply to the database
    @Transactional
    public void updateStudentName(Long studentId, String name)
    {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty())
        {
            throw new IllegalStateException("Student with id " + studentId + " does not exist.");
        }

        if (name != null)
        {
            studentOptional.get().setName(name);
        }
    }

    public Optional<Student> findStudentById(Long studentId)
    {

        return studentRepository.findById(studentId);
    }

    public boolean studentModelIsValid(Student student)
    {
        if (student.getName() == null || student.getEmail() == null || student.getDateOfBirth() == null)
        {
            return false;
        }

        if (student.getName().length() == 0 || student.getEmail().length() == 0)
        {
            return false;
        }

        //Add further validation
        return true;
    }

}
