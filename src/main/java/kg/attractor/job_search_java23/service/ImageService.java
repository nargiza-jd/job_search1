package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.AvatarUploadDto;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    String saveAvatar(AvatarUploadDto dto);
    ResponseEntity<?> getAvatarByName(String name);
}