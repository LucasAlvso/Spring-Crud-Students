package com.example.demo.user;

import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserService
{

    @Autowired
    UserRepository userRepository;

    public Optional<User> createUser(User user)
    {
        if (!isValid(user))
        {
            throw new IllegalStateException("Invalid Model");
        }
        if (!usernameIsAvailable(user.getUsername()))
        {
            throw new IllegalStateException("Username already taken");
        }

        return Optional.ofNullable(userRepository.save(user));
    }

    public Optional<User> findUserById(Long userId)
    {
        return handleDataAccessException( () ->userRepository.findById(userId));
    }

    public boolean isValid(User user)
    {
        if (user.getPassword() == null || user.getPassword().length() < 5)
        {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().length() < 5)
        {
            return false;
        }
        return true;
    }

    private <T> T handleDataAccessException(Supplier<T> supplier)
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

    public boolean usernameIsAvailable(String username)
    {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        return userOptional.isEmpty();
    }
}