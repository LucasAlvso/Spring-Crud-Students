package com.example.demo.user;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest
{

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void shouldSaveUser()
    {
        Optional<User> user = Optional.of(new User("Lucas", "senha", UserRole.ADMIN));

        when(userRepository.save(user.get())).thenReturn(user.get());

        Optional<User> result = userService.createUser(user.get());

        assertEquals(result, user);
    }

    @Test
    void shouldFindUserById()
    {
        Optional<User> userOptional = Optional.of(new User(20L, "Lucas", "senha", UserRole.ADMIN));

        when(userRepository.findById(20L)).thenReturn(userOptional);

        Optional<User> result = userService.findUserById(20L);

        assertEquals(result, userOptional);
    }

    @Test
    void shouldCheckIfUserIsValid()
    {
        User user = new User("Lucas", "senha", UserRole.ADMIN);

        assertTrue(userService.isValid(user));
    }

    @Test
    void shouldCheckIfUsernameExists()
    {
        User user = new User("Lucas", "senha", UserRole.ADMIN);

        when(userRepository.findUserByUsername("Lucas")).thenReturn(Optional.of(user));

        boolean result = userService.usernameIsAvailable(user.getUsername());

        assertFalse(result);
    }

    @Test
    void shouldCheckIfUserIsDeleted()
    {
        User user = new User(12L, "Lucas", "senha", UserRole.ADMIN);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUserById(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    void shouldReturnAllUsers()
    {
        User user = new User(12L, "Lucas", "senha", UserRole.ADMIN);
        User user2 = new User(13L, "Roberto", "senha", UserRole.USER);

        when(userRepository.findAll()).thenReturn(List.of(user, user2));
    }

    @Test
    void shouldReturnUserById()
    {
        Optional<User> userOptional = Optional.of(new User(12L, "Lucas", "senha", UserRole.ADMIN));

        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        Optional<User> result = userService.findUserById(12L);

        assertEquals(userOptional, result);
    }

}
