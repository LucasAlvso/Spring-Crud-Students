package com.example.demo.model.user;

import com.example.demo.model.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "application_user")
public class User
{
    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long id;
    private String username;

    private String password;

    private UserRole role;

    public User(String username, String password, UserRole role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String username, String password, UserRole role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User()
    {

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserRole getRole()
    {
        return role;
    }

    public void setRole(UserRole role)
    {
        this.role = role;
    }

    public long getId()
    {
        return this.id;
    }
}
