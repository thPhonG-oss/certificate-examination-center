package com.pptk.certificate_examination_center.controller;

import com.pptk.certificate_examination_center.dto.ApiResponseDto;
import com.pptk.certificate_examination_center.entity.ResponseStatus;
import com.pptk.certificate_examination_center.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageUploadController {

    @Autowired
    private ImageServiceImpl imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseDto<String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.uploadImage(file); //
            return ResponseEntity.ok(new ApiResponseDto<>(String.valueOf(ResponseStatus.SUCCESS), "Image uploaded successfully", imageUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(String.valueOf(ResponseStatus.FAILED), "Image upload failed: " + e.getMessage(), null));
        }
    }
}