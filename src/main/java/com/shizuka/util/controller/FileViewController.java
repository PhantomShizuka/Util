package com.shizuka.util.controller;

import com.shizuka.util.request.FileUploadRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("fileUploadRequest", new FileUploadRequest());
        return "upload";
    }
}
