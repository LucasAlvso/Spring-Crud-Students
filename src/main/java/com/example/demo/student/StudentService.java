package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;

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
        return dataAccessExpectionCommonHandler(studentRepository::findAll);
    }

    public void addNewStudent(Student student)
    {
        Optional<Student> studentByEmail = dataAccessExpectionCommonHandler(() -> studentRepository.findStudentByEmail(student.getEmail()));
        if (studentByEmail.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId)
    {
        boolean studentExists = dataAccessExpectionCommonHandler(() ->studentRepository.existsById(studentId));

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
        Optional<Student> studentOptional = dataAccessExpectionCommonHandler(() ->studentRepository.findById(studentId));

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
        return dataAccessExpectionCommonHandler(() ->studentRepository.findById(studentId));
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

    // Used common exception handler for database issues because the Repository annotation in the repository class
    // converts all database exceptions to DatabaseAccessException
    private <T> T dataAccessExpectionCommonHandler(Supplier<T> supplier)
    {
        try
        {
            return supplier.get();
        }
        catch (DataAccessException exception)
        {
            System.out.println("Error: " + exception.getMessage());
            return (T) Optional.empty();
        }
    }

}
