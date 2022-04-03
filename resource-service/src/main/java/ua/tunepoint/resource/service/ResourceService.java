package ua.tunepoint.resource.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResourceService<T> {

    T save(MultipartFile file);

    T findById(String id);
}
