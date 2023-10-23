package com.flayger.SpringPetProject.dto;

import com.flayger.SpringPetProject.models.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

//@Component
public class RealmPropertyDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "property name should not be empty")
    private String name;

    @Min(0)
    private int price;

    //@NotEmpty(message = "property location should not be empty")
    private String location;
    //google maps?

    @Min(0)
    private int size;

    @Min(0)
    private int number_of_bedrooms;
    @Min(0)
    private int number_of_bathrooms;

    private boolean is_closed;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private PersonDTO owner_id;


    public PersonDTO getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(PersonDTO owner_id) {
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber_of_bedrooms() {
        return number_of_bedrooms;
    }

    public void setNumber_of_bedrooms(int number_of_bedrooms) {
        this.number_of_bedrooms = number_of_bedrooms;
    }

    public int getNumber_of_bathrooms() {
        return number_of_bathrooms;
    }

    public void setNumber_of_bathrooms(int number_of_bathrooms) {
        this.number_of_bathrooms = number_of_bathrooms;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }
}
