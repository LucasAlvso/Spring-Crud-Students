package com.example.demo.user;

import com.example.demo.dto.UserDTO;
import com.example.demo.dtoconverter.UserDTOConverter;
import com.example.demo.model.user.User;
import com.example.demo.model.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDTOConverterTest
{

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Test
    void shouldConvertUserToDTO()
    {
        User user = new User(12L, "Lucas", "senha", UserRole.ADMIN);
        UserDTO result = userDTOConverter.convertToDTO(user);

        UserDTO userDTO = new UserDTO(12L, "Lucas", UserRole.ADMIN);

        assertEquals(result.getId(), userDTO.getId());
        assertEquals(result.getUsername(), userDTO.getUsername());
        assertEquals(result.getRole(), userDTO.getRole());
    }

}
