package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MultiPartController {

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
}
