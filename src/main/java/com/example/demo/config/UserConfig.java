package com.example.demo.config;

import com.example.demo.model.enums.UserRole;
import com.example.demo.model.student.Student;
import com.example.demo.model.user.User;
import com.example.demo.repository.student.StudentRepository;
import com.example.demo.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig
{
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository)
    {
        return args -> {
            User lucas = new User(12L, "Lucas", "senha", UserRole.ADMIN);
            User roberto = new User(13L, "Roberto", "senha123", UserRole.USER);

            repository.saveAll(List.of(lucas, roberto));
        };
    }
}
