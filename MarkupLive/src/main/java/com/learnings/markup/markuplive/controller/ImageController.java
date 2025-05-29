package com.learnings.markup.markuplive.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/image")
@Tag(name = "头像上传API", description = "通过这个接口可以上传头像，静态资源访问路径为/img/文件名")

public class ImageController {
    private static final String IMAGE_PATH = "/Users/lujianfeng/Desktop/repositoryImage/";


    @PostMapping("/uploadAvatar")
    @Operation(summary = "头像上传", description = "上传头像并返回url")
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> res = new HashMap<>();
        if (file.isEmpty() || file.getSize() == 0) {
            return Map.of("message", "File is empty");
        }
        try {
            String filename = Instant.now().toEpochMilli() + "_" + file.getOriginalFilename();
            Path path = Path.of(IMAGE_PATH, filename);
            Files.copy(file.getInputStream(), path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            String url = "http://localhost:8080/img/" + filename;
            res.put("status", "success");
            res.put("url", url);
            return res;


        }catch (Exception e) {
            e.printStackTrace();
            return Map.of("message", "File upload failed");
        }


    }
}
