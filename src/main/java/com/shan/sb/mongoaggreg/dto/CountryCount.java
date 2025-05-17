package com.shan.sb.mongoaggreg.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CountryCount {
    private String id; // this maps to _id
    private int count;
}
