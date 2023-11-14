package com.flayger.SpringPetProject.repositories;

import com.flayger.SpringPetProject.models.RealmProperty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmPropertiesRepository extends JpaRepository<RealmProperty, Integer>
        //,JpaSpecificationExecutor<RealmProperty> для спецификаций - замены Criteria API, сначала сделаю Criteria API
{

}
