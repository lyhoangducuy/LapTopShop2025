package com.latptop.flexuy.service.uploadFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;
    public FileSystemStorageService() {
        this.rootLocation = Paths.get("src/main/resources/static/uploads");
    }
    @Override
    public void init() {
       try {
        Files.createDirectory(rootLocation);
       } catch (Exception e) {
        // TODO: handle exception
       }
    }

    @Override
    public void storeFile(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING  );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
