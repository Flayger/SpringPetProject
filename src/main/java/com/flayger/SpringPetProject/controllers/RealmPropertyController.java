package com.flayger.SpringPetProject.controllers;

import com.flayger.SpringPetProject.models.Person;
import com.flayger.SpringPetProject.models.RealmProperty;
import com.flayger.SpringPetProject.security.PersonDetails;
import com.flayger.SpringPetProject.services.PeopleService;
import com.flayger.SpringPetProject.services.RealmPropertiesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/realmProperties")
public class RealmPropertyController {

//crud
    private final RealmPropertiesService realmPropertiesService;

    @Autowired
    public RealmPropertyController(RealmPropertiesService realmPropertiesService) {
        this.realmPropertiesService = realmPropertiesService;
    }

    @GetMapping("/show")
    public String show(Model model){
        //получить из бд список
        model.addAttribute("realmList", realmPropertiesService.findAll());
        return "/realmProperties/show";
    }

    @GetMapping("/new")
    public String newProperty(@ModelAttribute("realmProperty") RealmProperty realmProperty){
        return "/realmProperties/create";
    }

    @PreAuthorize("#realmProperty.owner_id.username == principal.username")
    @PostMapping("/save")
    public String save(@ModelAttribute("realmProperty") @Valid RealmProperty realmProperty, BindingResult bindingResult,
                       @AuthenticationPrincipal PersonDetails personDetails){
        //validate
        realmProperty.setOwner_id(personDetails.getPerson());
        if(bindingResult.hasErrors())
            return "/realmProperties/create";
        realmPropertiesService.save(realmProperty);
        return "redirect:/realmProperties/show";
    }

    @GetMapping("/{id}")
    public String showRealm(Model model, @PathVariable("id") int id){
        model.addAttribute("realmProperty", realmPropertiesService.findById(id).get());
        return "/realmProperties/showRealm";
    }

    @PreAuthorize("#personDetails.username == principal.username")
    @GetMapping("/{id}/edit")
    public String editProperty(@PathVariable("id") int id, Model model, @AuthenticationPrincipal PersonDetails personDetails){
        model.addAttribute("realmProperty", realmPropertiesService.findById(id).get());
        return "/realmProperties/edit";
    }

    @PreAuthorize("#realmProperty.owner_id.username == principal.username")
    @PatchMapping("/{id}")
    public String updateProperty(@ModelAttribute("realmProperty") @Valid RealmProperty realmProperty, BindingResult bindingResult,
                                 @AuthenticationPrincipal PersonDetails personDetails){
        realmProperty.setOwner_id(personDetails.getPerson());
        //validate
        if(bindingResult.hasErrors())
            return "/realmProperties/edit";
        realmPropertiesService.update(realmProperty);
        return "redirect:/realmProperties/show";
    }

    @PreAuthorize("#personDetails.username == principal.username")
    @DeleteMapping("/{id}/delete")
    public String deleteProperty(@PathVariable("id") int id, @AuthenticationPrincipal PersonDetails personDetails){
        //нужно подтверждение спросить? как вообще безопасно это реализовать? чтобы доступ к запросу был только после авторизации?
        realmPropertiesService.delete(id);
        return "redirect:/realmProperties/show";
    }

}
