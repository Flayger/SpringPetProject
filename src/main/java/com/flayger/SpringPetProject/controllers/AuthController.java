package com.flayger.SpringPetProject.controllers;

import com.flayger.SpringPetProject.dto.PersonDTO;
import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.services.RegistrationService;
import com.flayger.SpringPetProject.util.PersonValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, ModelMapper modelMapper) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/hello")
    public String helloPage(){
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") PersonDTO personDTO){
        return "registration";
    }

    //добавить email verification
    @PostMapping("/registration")
    public String register(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        //Person person = convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "registration";

        registrationService.register(person);
        return "redirect:/login";
    }

    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }
}
