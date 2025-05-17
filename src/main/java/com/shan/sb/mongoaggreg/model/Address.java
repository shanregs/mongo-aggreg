package com.shan.sb.mongoaggreg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String address;
    private String country;
    private String pincode;
    private String type;
}
