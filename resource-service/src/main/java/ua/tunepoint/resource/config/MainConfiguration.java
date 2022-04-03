package ua.tunepoint.resource.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.tunepoint.resource.config.props.StorageProperties;
import ua.tunepoint.web.exception.WebExceptionHandler;

@Configuration
public class MainConfiguration {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 client() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(storageProperties.getKeyId(), storageProperties.getSecret()))
                )
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(storageProperties.getHost(), "ru-central1")
                )
                .build();
    }

    @Bean
    public WebExceptionHandler webExceptionHandler() {
        return new WebExceptionHandler();
    }
}
