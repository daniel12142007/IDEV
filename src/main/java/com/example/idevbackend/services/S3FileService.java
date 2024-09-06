package com.example.idevbackend.services;

import com.example.idevbackend.exceptions.AwsException;
import com.example.idevbackend.models.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3FileService {

    private final S3Client s3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${s3.viewImage}")
    private String linkViewImage;

    public String upload(MultipartFile file) throws IOException {
        log.info("Uploading file ...");
        String key = System.currentTimeMillis() + file.getOriginalFilename();
        PutObjectRequest por = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3.putObject(por, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        log.info("Upload complete.");
        return key;
    }

    public String saveImage(MultipartFile image) {
        String linkImage = "";
        try {
            linkImage = linkViewImage + upload(image);
        } catch (Exception e) {
            throw new AwsException("An error occurred when trying to save the image.");
        }
        if (linkImage.isEmpty()) {
            throw new AwsException("Failed to save image");
        }
        return linkImage;
    }

    public Map<String, String> delete(String fileName) {
        log.info("Deleting file...");
        try {
            log.warn("Deleting object: {}", fileName);
            s3.deleteObject(dor -> dor.bucket(bucketName).key(fileName).build());
        } catch (S3Exception e) {
            throw new IllegalStateException(e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
        return Map.of(
                "message", fileName + " has been deleted."
        );
    }

    public ResponseEntity<InputStreamResource> viewImage(String filename) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        InputStream fileStream = s3.getObject(getObjectRequest, ResponseTransformer.toInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(new InputStreamResource(fileStream), headers, HttpStatus.OK);
    }

    public ResponseEntity<InputStreamResource> viewVideo(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        InputStream fileStream = s3.getObject(getObjectRequest, ResponseTransformer.toInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));

        return new ResponseEntity<>(new InputStreamResource(fileStream), headers, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> listFiles() {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response listObjectsResponse = s3.listObjectsV2(listObjectsRequest);
        List<String> fileNames = listObjectsResponse.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());

        return ResponseEntity.ok(fileNames);
    }
}