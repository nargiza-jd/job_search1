package kg.attractor.job_search_java23.controller;

import kg.attractor.job_search_java23.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final FileUtil fileUtil;

    @PostMapping
    public String uploadAvatar(@RequestParam("file") MultipartFile file) {
        return fileUtil.saveUploadFile(file, "avatars/");
    }

    @GetMapping("{filename}")
    public ResponseEntity<?> getAvatar(@PathVariable String filename) {
        return fileUtil.getOutputFile(filename, "avatars/", MediaType.IMAGE_JPEG);
    }
}