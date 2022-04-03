package ua.tunepoint.resource.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.tunepoint.resource.config.props.StorageProperties;
import ua.tunepoint.web.exception.ServerException;

import java.io.File;

@Component
@Slf4j
@RequiredArgsConstructor
public class YandexStorageService implements StorageService {

    private final StorageProperties storageProperties;
    private final AmazonS3 client;

    @Override
    public void save(File persistedFile, String key) {
        PutObjectRequest request = new PutObjectRequest(storageProperties.getBucketName(), key, persistedFile);

        try {
            log.info("Yandex PUT request started");
            client.putObject(request);
            log.info("Yandex PUT request completed");

        } catch (SdkClientException ex) {
            log.error("Error occurred while executing PUT request", ex);

            throw new ServerException("Oops, error occurred while saving data");
        }
    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest request = new DeleteObjectRequest(storageProperties.getBucketName(), key);

        try {
            log.info("Yandex DELETE request started");
            client.deleteObject(request);
            log.info("Yandex DELETE request completed");
        } catch (SdkClientException ex) {
            log.error("Error occurred while executing DELETE request");

            throw new ServerException("Oops, error occurred while deleting data");
        }
    }
}
