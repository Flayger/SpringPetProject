package com.flayger.SpringPetProject.services;

import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username){
        return peopleRepository.findUserByUsername(username);
    }


    public Optional<Person> findById(int id){
        return peopleRepository.findById(id);
    }

    @Transactional()
    public void update(Person person){
        Optional<Person> dbPerson = peopleRepository.findById(person.getId());
        if(dbPerson.isPresent()){
            dbPerson.get().setUsername(person.getUsername());
            dbPerson.get().setPropertyList(person.getPropertyList());
            dbPerson.get().setUpdatedAt(LocalDateTime.now());
            peopleRepository.save(dbPerson.get());
        }
    }

    @Transactional()
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Person> show(){
        return peopleRepository.findAll();
    }
}
