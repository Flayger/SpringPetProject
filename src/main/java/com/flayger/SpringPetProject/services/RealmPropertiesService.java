package com.flayger.SpringPetProject.services;

import com.flayger.SpringPetProject.models.RealmProperty;
import com.flayger.SpringPetProject.repositories.RealmPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealmPropertiesService {

    private final RealmPropertiesRepository realmPropertiesRepository;


    @Autowired
    public RealmPropertiesService(RealmPropertiesRepository realmPropertiesRepository) {
        this.realmPropertiesRepository = realmPropertiesRepository;
    }


    public void save(RealmProperty realmProperty) {
        realmPropertiesRepository.save(realmProperty);
    }

    public Optional<RealmProperty> findById(int id) {
        return realmPropertiesRepository.findById(id);
    }

    public void update(RealmProperty realmProperty) {
        Optional<RealmProperty> dbProperty = realmPropertiesRepository.findById(realmProperty.getId());
        if(dbProperty.isPresent()){
            dbProperty.get().setLocation(realmProperty.getLocation());
            dbProperty.get().setName(realmProperty.getName());
            dbProperty.get().setIs_closed(realmProperty.isIs_closed());
            dbProperty.get().setPrice(realmProperty.getPrice());
            dbProperty.get().setSize(realmProperty.getSize());
            dbProperty.get().setOwner_id(realmProperty.getOwner_id());
            dbProperty.get().setNumber_of_bedrooms(realmProperty.getNumber_of_bedrooms());
            dbProperty.get().setNumber_of_bathrooms(realmProperty.getNumber_of_bathrooms());
            realmPropertiesRepository.save(dbProperty.get());
        }
    }

    public List<RealmProperty> findAll(){
        return realmPropertiesRepository.findAll();
    }

    public void delete(int id) {
        realmPropertiesRepository.deleteById(id);
    }
}
