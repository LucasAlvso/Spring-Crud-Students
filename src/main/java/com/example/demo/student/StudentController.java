package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController
{

    private final StudentService studentService;
    private final StudentDTOConverter studentDTOConverter;

    @Autowired
    public StudentController(StudentService studentService, StudentDTOConverter studentDTOConverter)
    {
        this.studentService = studentService;
        this.studentDTOConverter = studentDTOConverter;
    }


    //Returns list of DTOs in order to avoid exposing user email and ID
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents()
    {
        List<StudentDTO> dtoList = new LinkedList<>();
        List<Student> modelList = studentService.getStudents();

        for (Student student: modelList)
        {
            dtoList.add(studentDTOConverter.convertStudentToDTO(student));
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // Returns DTO because the response body shouldn't expose email and id because in this application they're immutable
    // and the response body should include changed info for frontend visualization.
    @PostMapping
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody Student student)
    {
        if (!studentService.studentModelIsValid(student))
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        StudentDTO dtoBody = studentDTOConverter.convertStudentToDTO(student);

        studentService.addNewStudent(student);

        return new ResponseEntity<>(dtoBody, HttpStatus.CREATED);
    }

    // Deletes student that was searched by ID
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable("studentId") Long studentId)
    {
        Optional<Student> studentOptional = studentService.findStudentById(studentId);

        if (studentOptional.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        studentService.deleteStudent(studentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Searches student by ID and replaces their name
    @PutMapping(path = "{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("studentId")Long studentId, @RequestParam(required = false) String name)
    {
        Optional<Student> studentOptional = studentService.findStudentById(studentId);


        if (studentOptional.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        //Created a model and replaced name on memory in order to use the same validation method
        Student modelAfterNameChange = studentOptional.get();
        modelAfterNameChange.setName(name);
        if (!studentService.studentModelIsValid(modelAfterNameChange))
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        StudentDTO dtoBody = studentDTOConverter.convertStudentToDTO(studentOptional.get());
        studentService.updateStudentName(studentId, name);

        return new ResponseEntity<>(dtoBody, HttpStatus.OK);
    }

}
