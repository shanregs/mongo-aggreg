package com.shan.sb.mongoaggreg.controller;

import com.shan.sb.mongoaggreg.model.Person;
import com.shan.sb.mongoaggreg.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/country/{country}")
    public List<Person> getByCountry(@PathVariable String country) {
        return service.getByCountry(country);
    }

    @GetMapping("/age-range")
    public List<Person> getByAgeRange(@RequestParam int min, @RequestParam int max) {
        return service.getByAgeRange(min, max);
    }

    @GetMapping("/sorted-age")
    public List<Person> getSortedByAge() {
        return service.sortByAge();
    }

    @GetMapping("/group-by-country")
    public List<Document> groupByCountry() {
        return service.groupByCountry();
    }
}
