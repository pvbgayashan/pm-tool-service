package com.pm.tool.service;

import com.pm.tool.entity.Upload;
import com.pm.tool.repository.UploadRepository;
import com.pm.tool.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UploadService {

    private final UploadRepository uploadRepository;
    private final StorageService storageService;

    @Autowired
    public UploadService(UploadRepository uploadRepository,
                         StorageService storageService) {
        this.uploadRepository = uploadRepository;
        this.storageService = storageService;
    }

    public List<Upload> getAllUploads() {
        List<Upload> uploads = new ArrayList<>();
        uploadRepository.findAll().forEach(uploads::add);
        return uploads;
    }

    public Optional<Upload> getUploadById(Long id) {
        return uploadRepository.findById(id);
    }

    public void saveUpload(Upload upload, MultipartFile inputFile) {
        boolean uploaded = storageService.store(inputFile);
        if (uploaded) { // save upload data when file uploaded
            uploadRepository.save(upload);
        }
    }

    public void updateUpload(Upload upload) {
        uploadRepository.save(upload);
    }

    public void deleteUploadById(Long id) {
        uploadRepository.deleteById(id);
    }

}
