package com.shizuka.util.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadRequest {

    @NotNull(message = "El archivo no puede estar vacío")
    private MultipartFile file;

    @NotEmpty(message = "El nuevo nombre del archivo no puede estar vacío")
    private String newFileName;
}