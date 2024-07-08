package com.shizuka.util.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FileError implements IErrorCode {
    SIZE("El archivo excede el tamaño máximo permitido", HttpStatus.PAYLOAD_TOO_LARGE),
    TYPE("Solo se permiten archivos DOCX", HttpStatus.BAD_REQUEST),
    STORAGE("Error al guardar el archivo", HttpStatus.INTERNAL_SERVER_ERROR),
    DOWNLOAD("Error al descargar el archivo", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    FileError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}