package com.flayger.SpringPetProject.repositories;

import com.flayger.SpringPetProject.models.RealmProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmPropertiesRepository extends JpaRepository<RealmProperty, Integer> {
}
