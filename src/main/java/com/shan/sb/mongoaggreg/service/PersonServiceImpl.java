package com.shan.sb.mongoaggreg.service;

import com.shan.sb.mongoaggreg.dto.CountryCount;
import com.shan.sb.mongoaggreg.model.Person;
import com.shan.sb.mongoaggreg.repository.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
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

    /**
     db.persons.find({ "addresses.country": "India" })

     * @param country
     * @return
     */
    @Override
    public List<Person> getByCountry(String country) {
        return personRepository.findByAddresses_Country(country);
    }

    /**
     db.persons.find({ age: { $gte: 20, $lte: 40 } })
     * @param min
     * @param max
     * @return
     */

    @Override
    public List<Person> getByAgeRange(int min, int max) {
        return personRepository.findByAgeBetween(min,max);
    }

    /**
     db.persons.find().sort({ age: 1 })
     * @return
     */
    @Override
    public List<Person> sortByAge() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    /**
    db.persons.aggregate([
    { $unwind: "$addresses" },
    { $group: { _id: "$addresses.country", count: { $sum: 1 } } },
    { $sort: { count: -1 } },
    { $limit: 5 }
    ])
    */
    public List<CountryCount> groupByCountry() {
        Aggregation agg = newAggregation(
                unwind("$addresses"),
                group("$addresses.country").count().as("count"),
                sort(Sort.by(Sort.Direction.DESC, "count")),
                limit(5)
        );
        return mongoTemplate.aggregate(agg, "persons", CountryCount.class).getMappedResults();
    }

    /**
     *
      db.persons.aggregate([
        { $unwind: "$tags" },
        { $group: { _id: "$tags", count: { $sum: 1 } } },
        { $sort: { count: -1 } }
      ])
     * @return
     */
    @Override
    public List<String> getAllTags() {
        Aggregation agg = newAggregation(
                unwind("$tags"),
                group("$tags").count().as("count"),
                sort(Sort.by(Sort.Direction.DESC, "count"))
        );
        return mongoTemplate.aggregate(agg, "persons", org.bson.Document.class)
                .getMappedResults().stream().map(d-> d.getString("_id")).toList();
    }


    /**
      db.persons.aggregate([
        { $group: { _id: "$eyeColor", count: { $sum: 1 } } },
        { $sort: { count: -1 } }
      ])
     * @return
     */
    @Override
    public List<String> getAllEyeColors() {
        Aggregation agg = newAggregation(
                group("$eyeColor").count().as("count"),
                sort(Sort.by(Sort.Direction.DESC, "count"))
        );
        return mongoTemplate.aggregate(agg, "persons", org.bson.Document.class)
                .getMappedResults().stream().map(d -> d.getString("_id")).toList();
    }

    /**
     db.persons.aggregate([
     { $group: { _id: "$company.title", count: { $sum: 1 } } },
     { $sort: { count: -1 } }
     ])
     * @return
     */
    @Override
    public List<String> getAllCompanyTitles() {
        Aggregation agg = newAggregation(
                group("$company.title").count().as("count"),
                sort(Sort.by(Sort.Direction.DESC, "count"))
        );
        return mongoTemplate.aggregate(agg, "persons", org.bson.Document.class)
                .getMappedResults().stream().map(d -> d.getString("_id")).toList();
    }

}
