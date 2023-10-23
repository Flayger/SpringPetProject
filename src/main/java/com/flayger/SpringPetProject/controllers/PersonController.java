package com.flayger.SpringPetProject.controllers;

import com.flayger.SpringPetProject.dto.PersonDTO;
import com.flayger.SpringPetProject.dto.RealmPropertyDTO;
import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.models.RealmProperty;
import com.flayger.SpringPetProject.security.PersonDetails;
import com.flayger.SpringPetProject.services.PeopleService;
import com.flayger.SpringPetProject.util.MapperUtil;
import com.flayger.SpringPetProject.util.PersonValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class PersonController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PeopleService peopleService, ModelMapper modelMapper, PersonValidator personValidator, MapperUtil mapperUtil) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
        this.personValidator = personValidator;
    }

    @GetMapping("/show")
    public String showUsers(Model model){
        //список из бд
        model.addAttribute("userList", peopleService.show());//.stream().map(this::convertToPersonDTO).collect(Collectors.toList()));
        return "/users/show";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(Model model){
        //список из бд
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("user", personDetails.getPerson());//convertToPersonDTO(personDetails.getPerson()));
        return "/users/userInfo";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        //страница пользователя со списком его недвижимости
        model.addAttribute("user", peopleService.findById(id).get());//convertToPersonDTO(peopleService.findById(id).get()));
        return "/users/showUser";
    }

//    @GetMapping("/new")
//    public String createUser(@ModelAttribute("user") Person person){
//        return "/users/create";
//    }
//
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("user") @Valid Person person, BindingResult bindingResult){
//        //validate
//        if(bindingResult.hasErrors())
//            return "/users/create";
//        peopleService.save(person);
//        return "redirect:/show";
//    }

    @GetMapping("/edit")
    public String editUser(Model model, @AuthenticationPrincipal PersonDetails personDetails){
        model.addAttribute("user", personDetails.getPerson());//convertToPersonDTO(personDetails.getPerson()));//peopleService.findById(id).get());
        return "/users/edit";
    }

    @PreAuthorize("#person.username == principal.username")
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid Person person, BindingResult bindingResult){

        //Person person = convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);
        //нужен тут id, чтобы по бд искать или он в сущности уже будет?
        //validate
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "/users/edit";
        }
        peopleService.update(person);
        return "redirect:/users/show";
    }

    //@PreAuthorize("#person.username == principal.username")
    @PreAuthorize("#person.username == principal.username")
    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, HttpServletRequest request) throws ServletException {
        //нужно подтверждение спросить? как вообще безопасно это реализовать? чтобы доступ к запросу был только после авторизации?
        peopleService.delete(id);
        request.logout();
        return "redirect:/login";
    }

    private Person convertToPerson(PersonDTO personDTO){

        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person){
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
                //нужно добавить здесь конвертацию realmsList в DTO.
       // personDTO.setRealmPropertyDTOList(MapperUtil.convertList(person.getRealmPropertyList(), this::convertToRealmDTO));
        return personDTO;
    }

    private RealmPropertyDTO convertToRealmDTO(RealmProperty realmProperty){
//        PostDto postDto = modelMapper.map(post, PostDto.class);
//        postDto.setUserDto(convertToUserDto(post.getUser()));
//        return  ;

        RealmPropertyDTO realmPropertyDTO =  modelMapper.map(realmProperty, RealmPropertyDTO.class);
       // realmPropertyDTO.setOwner_id(convertToPersonDTO(realmProperty.getOwner_id()));
        return realmPropertyDTO;
    }

}
