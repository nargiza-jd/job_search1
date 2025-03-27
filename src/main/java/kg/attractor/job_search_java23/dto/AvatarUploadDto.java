package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarUploadDto {
    @NotNull(message = "Файл не должен быть пустым")
    private MultipartFile file;
}