package com.latptop.flexuy.service.uploadFile;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    void storeFile(MultipartFile file );
}
