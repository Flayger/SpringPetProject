package com.flayger.SpringPetProject.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

//@Component
public class PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 2, max = 255, message = "Размер имени должен быть от 2 до 255 символов")
    private String username;

    private String password;

    //нужно?
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner_id")
    private List<RealmPropertyDTO> realmPropertyDTOList;


    public String getRole() {
        return role;
    }

    public List<RealmPropertyDTO> getRealmPropertyDTOList() {
        return realmPropertyDTOList;
    }

    public void setRealmPropertyDTOList(List<RealmPropertyDTO> realmPropertyDTOList) {
        this.realmPropertyDTOList = realmPropertyDTOList;
    }

    public void setRole(String role) {
        this.role = role;
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

}
