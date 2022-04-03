package ua.tunepoint.resource.data.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ua.tunepoint.resource.data.entity.AudioResource;
import ua.tunepoint.resource.data.entity.Resource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AudioResourceRepository implements ResourceRepository<AudioResource> {

    private final MongoOperations operations;

    @Override
    public AudioResource save(AudioResource resource) {
        return operations.save(resource);
    }

    @Override
    public void remove(AudioResource resource) {
        operations.remove(resource);
    }

    @Override
    public Optional<AudioResource> findById(String id) {
        return Optional.ofNullable(operations.findById(id, AudioResource.class));
    }

    @Override
    public List<AudioResource> findByHash(String hash) {
        if (hash == null) {
            return Collections.emptyList();
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("md5Hash").is(hash));
        return operations.find(query, AudioResource.class);
    }
}
