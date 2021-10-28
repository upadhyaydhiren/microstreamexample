package com.example.microstream.repo;

import com.example.microstream.model.Customer;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface CustomerRepository {

    void add(Customer customer);

    List<Customer> findAll();

    List<Customer> findByFirstName(String firstName);

    void deleteById(Long id);

    void deleteAll();

    default <T> T getAll(Supplier<T> supplier) {
        return supplier.get();
    }

    default <T, Y extends Collection<T>> Stream<T> getAllStream(Supplier<Y> supplier) {
        return getAll(supplier).stream();
    }

    default <T> void save(EmbeddedStorageManager embeddedStorageManager, T obj) {
        embeddedStorageManager.store(obj);
    }
}
