package ua.tunepoint.resource.service.converter;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ResourceConverter<T> {

    T convert(MultipartFile multipart, File persisted);
}
