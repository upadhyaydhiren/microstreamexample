package com.example.microstream.config;

import com.example.microstream.model.Customer;
import lombok.Data;
import one.microstream.reference.Lazy;

import java.util.ArrayList;
import java.util.List;


@Data
public class CustomerApp {
    private final Lazy<List<Customer>> customers;

    public CustomerApp() {
        this.customers = Lazy.Reference(new ArrayList<>());
    }
}
