package com.example.idevbackend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Getter
@Setter
public class AmazonConfig {

    @Value("${aws.accessKey}")
    private String AWS_ACCESS_KEY_ID;

    @Value("${aws.secretKey}")
    private String AWS_SECRET_ACCESS_KEY;

    @Value("${aws.region}")
    private String REGION;

    @Bean
    public S3Client s3Client() {
        Region region = Region.of(REGION);

        final AwsBasicCredentials credentials = AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);

        return S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}