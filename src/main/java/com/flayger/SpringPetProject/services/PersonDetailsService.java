package com.flayger.SpringPetProject.services;

import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.repositories.PeopleRepository;
import com.flayger.SpringPetProject.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = peopleRepository.findUserByUsername(username);

        if(optionalPerson.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(optionalPerson.get());
    }
}
