package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dto.AvatarUploadDto;
import kg.attractor.job_search_java23.service.ImageService;
import kg.attractor.job_search_java23.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileUtil fileUtil;

    @Override
    public String saveAvatar(AvatarUploadDto dto) {
        return fileUtil.saveUploadFile(dto.getFile(), "avatars/");
    }

    @Override
    public ResponseEntity<?> getAvatarByName(String name) {
        return fileUtil.getOutputFile(name, "avatars/", MediaType.IMAGE_JPEG);
    }
}