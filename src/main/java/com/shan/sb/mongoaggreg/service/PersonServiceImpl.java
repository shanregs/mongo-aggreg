package com.shan.sb.mongoaggreg.service;

import com.shan.sb.mongoaggreg.model.Person;
import com.shan.sb.mongoaggreg.repository.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;


    private final MongoTemplate mongoTemplate;

    public PersonServiceImpl(PersonRepository personRepository, MongoTemplate mongoTemplate) {
        this.personRepository = personRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Person> getByCountry(String country) {
        return personRepository.findByAddresses_Country(country);
    }

    @Override
    public List<Person> getByAgeRange(int min, int max) {
        return personRepository.findByAgeBetween(min,max);
    }

    @Override
    public List<Person> sortByAge() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    @Override
    public List<Document> groupByCountry() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("$addresses"),
                Aggregation.group("$addresses.country").count().as("count"),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "count")),
                Aggregation.limit(5)
                );
        return mongoTemplate.aggregate(aggregation, "persons",
                Document.class).getMappedResults();
    }
}
