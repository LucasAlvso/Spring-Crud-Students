package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig
{
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository)
    {
        return args -> {
            User lucas = new User(12L, "Lucas", "senha123", UserRole.ADMIN);
            User roberto = new User(13L, "Roberto", "senha456", UserRole.USER);

            repository.saveAll(List.of(lucas, roberto));
        };
    }
}
