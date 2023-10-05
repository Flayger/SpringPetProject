package com.flayger.SpringPetProject.repositories;

import com.flayger.SpringPetProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

    Optional<Person> findUserByUsername(String username);
}
