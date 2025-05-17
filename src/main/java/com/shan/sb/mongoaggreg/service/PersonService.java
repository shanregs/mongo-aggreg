package com.shan.sb.mongoaggreg.service;

import com.shan.sb.mongoaggreg.model.Person;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public interface PersonService {
    public List<Person> getByCountry(String country);

    public List<Person> getByAgeRange(int min, int max) ;
    public List<Person> sortByAge() ;

    public List<Document> groupByCountry() ;
}
