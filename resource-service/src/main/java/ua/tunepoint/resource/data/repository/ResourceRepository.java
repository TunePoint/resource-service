package ua.tunepoint.resource.data.repository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository<T> {

    T save(T resource);

    void remove(T resource);

    Optional<T> findById(String id);

    List<T> findByHash(String hash);
}
