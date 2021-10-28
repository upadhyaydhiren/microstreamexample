package com.example.microstream.web;

import com.example.microstream.model.Customer;
import com.example.microstream.repo.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public record CustomerController(CustomerRepository customerRepository) {

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<List<Customer>> findByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(customerRepository.findByFirstName(firstName));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> create(@RequestBody Customer customer) {
        customerRepository.add(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAll() {
        customerRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
