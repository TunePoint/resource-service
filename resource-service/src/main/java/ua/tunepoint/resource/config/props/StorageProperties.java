package ua.tunepoint.resource.config.props;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "yandex")
public class StorageProperties {

    private String keyId;
    private String secret;
    private String host;
    private String bucketName;
}
