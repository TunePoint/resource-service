package ua.tunepoint.resource.service;

import java.io.File;

public interface StorageService {

    void save(File file, String key);

    void delete(String key);
}
