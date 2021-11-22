package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 S3Client;


    @Autowired
    public StorageService(AmazonS3 s3Client) {
        this.S3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file;
        File fileObj = convertMultiPartFileToFile(file);
        S3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded: " + fileName;

    }

    public byte[] downloadFile(String fileName) {
        final S3Object object = S3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = object.getObjectContent();
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public String deleteFile(String fileName) {
        S3Client.deleteObject(bucketName,fileName);
        return fileName+ " removed";
    }


}
