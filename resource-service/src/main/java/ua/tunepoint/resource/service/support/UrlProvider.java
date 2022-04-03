package ua.tunepoint.resource.service.support;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.tunepoint.resource.config.props.StorageProperties;
import ua.tunepoint.resource.data.entity.Resource;

@Component
@RequiredArgsConstructor
public class UrlProvider {

    private static final String PATTERN = "https://%s/%s/%s";

    private final StorageProperties properties;

    public String urlFor(Resource resource) {
        return String.format(PATTERN, properties.getHost(), properties.getBucketName(), resource.getStorageKey());
    }
}
