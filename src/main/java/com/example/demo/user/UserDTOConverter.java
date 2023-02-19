package com.example.demo.user;

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
