package com.pm.tool.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.tool.service.UploadService;
import com.pm.tool.entity.Upload;
import com.pm.tool.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;
    private final StorageService storageService;

    @Autowired
    public UploadController(UploadService uploadService,
                            StorageService storageService) {
        this.uploadService = uploadService;
        this.storageService = storageService;
    }

    @GetMapping("/get-all")
    public List<Upload> getAllUploads() {
        return uploadService.getAllUploads();
    }

    @GetMapping("/get-by-id/{id}")
    public Optional<Upload> getUploadById(@PathVariable Long id) {
        return uploadService.getUploadById(id);
    }

    @PostMapping("/save")
    public void saveUpload(@RequestParam("uploadString") String uploadString,
                           @RequestParam("inputFile") MultipartFile inputFile) {

        // map string to object
        ObjectMapper uploadObjectMapper = new ObjectMapper();
        Upload upload = null;
        try {
            upload = uploadObjectMapper.readValue(uploadString, Upload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // save upload data and file
        if (upload != null && inputFile != null) {
            uploadService.saveUpload(upload, inputFile);
        }

    }

    @PutMapping("/update")
    public void updateUpload(@RequestBody Upload upload) {
        uploadService.updateUpload(upload);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteUploadById(@PathVariable Long id) {
        uploadService.deleteUploadById(id);
    }

    @GetMapping("/get-file/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
