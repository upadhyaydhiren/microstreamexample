package com.example.microstream.repo;

import com.example.microstream.config.CustomerApp;
import com.example.microstream.model.Customer;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerApp customerApp;
    private final EmbeddedStorageManager storage;

    public CustomerRepositoryImpl(@Value("${microstream.store.location}") String path) {
        this.customerApp = new CustomerApp();
        this.storage = EmbeddedStorage.start(customerApp, Paths.get(path));
    }

    @Override
    public void add(Customer customer) {
        List<Customer> loadedCustomers = findAll();
        loadedCustomers.add(customer);
        save(storage, loadedCustomers);
    }

    @Override
    public List<Customer> findAll() {
        return customerApp.getCustomers().get();
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return findAll()
                .stream()
                .filter(c -> c.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        List<Customer> loadedCustomers = findAll();
        loadedCustomers.removeIf(customer -> customer.getCustomerNumber().equals(id));
        save(storage, loadedCustomers);
    }

    @Override
    public void deleteAll() {
        List<Customer> loadedCustomers = findAll();
        loadedCustomers.clear();
        save(storage, loadedCustomers);
    }

    @PreDestroy
    public void clean() {
        storage.shutdown();
    }
}
