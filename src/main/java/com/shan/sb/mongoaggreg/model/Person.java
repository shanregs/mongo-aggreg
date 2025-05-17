package com.shan.sb.mongoaggreg.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Boolean isActive;
    private String gender;
    private String registered;
    private String eyeColor;
    private String favoriteFruit;
    private Company company;
    private List<String> tags;
    private List<Address> addresses;
}
