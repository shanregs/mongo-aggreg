package com.shan.sb.mongoaggreg.service;

import com.shan.sb.mongoaggreg.dto.CountryCount;
import com.shan.sb.mongoaggreg.model.Person;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;

public interface PersonService {
    public List<Person> getByCountry(String country);

    public List<Person> getByAgeRange(int min, int max) ;
    public List<Person> sortByAge() ;

    public List<CountryCount> groupByCountry() ;
    public List<String> getAllTags();
    public List<String> getAllEyeColors();
    public List<String> getAllCompanyTitles() ;
}
