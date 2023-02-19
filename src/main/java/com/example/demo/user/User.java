package com.example.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User
{
    @Id
    private String username;

    private String password;

    private UserRole role;

    public User(String username, String password, UserRole role)
    {
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
}
