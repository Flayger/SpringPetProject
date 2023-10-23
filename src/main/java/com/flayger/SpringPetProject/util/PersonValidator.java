package com.flayger.SpringPetProject.util;

import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person testPerson = (Person) target;
        Optional<Person> dbPerson = peopleService.findByUsername(testPerson.getUsername());
        if(dbPerson.isPresent()){
            if(dbPerson.get().getId() == (testPerson.getId())){
                return;
            }
            errors.rejectValue("username", "", "это имя уже занято");
        }
    }
}
