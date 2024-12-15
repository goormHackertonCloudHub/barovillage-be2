package com.cloudhub.barovillage.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.base-url}")
    private String baseUrl;


    public String uploadImage(MultipartFile file) {
        try {
            // 고유한 파일 이름 생성
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // S3에 업로드 요청
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            // 액세스 가능한 URL 반환
            return baseUrl + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image to S3", e);
        }
    }
}