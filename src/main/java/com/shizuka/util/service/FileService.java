package com.shizuka.util.service;

import com.shizuka.util.error.FileError;
import com.shizuka.util.exception.ServiceException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService {

    private static final String UPLOADED_FOLDER = "uploads/";
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    public String saveFile(MultipartFile file, String newFileName) {
        try {
            // Validar tamaño del archivo
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new ServiceException(FileError.SIZE);
            }

            // Validar tipo de archivo
            if (!Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                throw new ServiceException(FileError.TYPE);
            }

            Path uploadPath = Paths.get(UPLOADED_FOLDER);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            byte[] bytes = file.getBytes();
            Path path = uploadPath.resolve(newFileName + ".docx");
            Files.write(path, bytes);

            return "Archivo subido exitosamente con el nombre '" + newFileName + "'";

        } catch (IOException e) {
            throw new ServiceException(FileError.STORAGE, e);
        }
    }

    public ResponseEntity<InputStreamResource> downloadFile(String fileName) {
        try {
            File file = new File(UPLOADED_FOLDER + fileName + ".docx");
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);

        } catch (IOException e) {
            throw new ServiceException(FileError.DOWNLOAD, e);
        }
    }
}
