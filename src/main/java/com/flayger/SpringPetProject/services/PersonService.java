package com.flayger.SpringPetProject.services;

import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username){
        return peopleRepository.findUserByUsername(username);
    }
}
