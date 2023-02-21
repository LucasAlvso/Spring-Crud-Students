package com.example.demo.dtoconverter;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter
{
    public UserDTOConverter()
    {

    }

    public UserDTO convertToDTO(User user)
    {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
