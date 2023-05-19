package com.etech.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
@Getter @Setter
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    @Value("${image.upload.dir.products}")
    private String productsUploadDir;
    @Value("${image.upload.dir.categories}")
    private String categoriesUploadDir;

    @GetMapping("product/{fileName}")
    public ResponseEntity<Resource> downloadProductImage(@PathVariable("fileName") String fileName) {
        return downloadImage(fileName, productsUploadDir);
    }

    @GetMapping("category/{fileName}")
    public ResponseEntity<?> downloadCategoryImage(@PathVariable("fileName") String fileName) {
        return downloadImage(fileName, categoriesUploadDir);
    }

    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file) {
        return uploadImage(file, productsUploadDir);
    }

    public ResponseEntity<?> uploadCategoryImage(@RequestParam("file") MultipartFile file) {
        return uploadImage(file, categoriesUploadDir);
    }

    private ResponseEntity<?> uploadImage(MultipartFile file, String uploadDir) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            String contentType = file.getContentType();
            assert contentType != null;
            if (!contentType.equals(MediaType.IMAGE_PNG_VALUE) && !contentType.equals("image/svg+xml")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недопустимий тип файлу. Допустимі тільки PNG та SVG файли");
            }

            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private ResponseEntity<Resource> downloadImage(String fileName, String downloadDir) {
        try {
            Path filePath = Paths.get(downloadDir).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            }
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}