package com.shizuka.util.controller;

import com.shizuka.util.exception.BindingException;
import com.shizuka.util.request.FileUploadRequest;
import com.shizuka.util.response.ApiResponse;
import com.shizuka.util.service.FileService;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/files")
public class FileApiController {

    private final FileService fileService;

    public FileApiController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ApiResponse<?> uploadFile(@Valid @ModelAttribute FileUploadRequest fileUploadRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }

        String responseMessage = fileService.saveFile(fileUploadRequest.getFile(), fileUploadRequest.getNewFileName());

        return ApiResponse.success(responseMessage);
    }

    @GetMapping("/download")
    public ApiResponse<?> downloadFile(@RequestParam String fileName) {
        return ApiResponse.success(fileService.downloadFile(fileName));
    }
}