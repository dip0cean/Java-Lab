package com.spring.boot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.*;

@Controller
@RequiredArgsConstructor
public class MultiPartController {

    private final ResourceLoader resourceLoader;

    @GetMapping("/upload")
    public String upload() {
        return "/file";
    }

    @PostMapping("/upload")
    public String upload(@RequestPart MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = "업로드 한 파일명 : " + file.getOriginalFilename();
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/upload";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        File     file     = resource.getFile();

        Tika   tika      = new Tika();
        String mediaType = tika.detect(file);

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachement;filename=\"" + resource.getFilename() + "\"")
                .header(CONTENT_TYPE, mediaType)
                .header(CONTENT_LENGTH, String.valueOf(file.length()))
                .body(resource);
    }
}
