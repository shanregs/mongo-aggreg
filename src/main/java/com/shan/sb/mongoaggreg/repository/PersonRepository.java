package com.shan.sb.mongoaggreg.repository;

import com.shan.sb.mongoaggreg.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByName(String name);
    List<Person> findByAddresses_Country(String country);
    List<Person> findByAgeBetween(int min, int max);
}
