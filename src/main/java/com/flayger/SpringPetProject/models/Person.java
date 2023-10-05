package com.flayger.SpringPetProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Component
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 2, max = 255, message = "Размер имени должен быть от 2 до 255 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "password shouldn't be empty")
    @Column(name = "password")
    private String password;

    @Min(value = 1900, message = "year shouldn't be below 1900")
    @Column(name = "year")
    private int year;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
