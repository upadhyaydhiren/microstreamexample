package com.example.microstream.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    String firstName;
    String lastName;
    Long customerNumber;
}
