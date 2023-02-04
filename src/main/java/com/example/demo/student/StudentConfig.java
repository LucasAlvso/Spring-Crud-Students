package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student lucas = new Student(1L, "Lucas", LocalDate.of(2003, Month.JULY, 25), "lucas.soares008@edu.pucrs.br");
            Student pedro = new Student("Pedro", LocalDate.of(2002, Month.APRIL, 18), "pedro@gmail.com");

            repository.saveAll(List.of(lucas, pedro));
        };
    }
}
