package kg.attractor.job_search_java23.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUtil {

    private static final String UPLOAD_DIR = "data/";

    public String saveUploadFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        try {
            Path pathDir = Paths.get(UPLOAD_DIR + subDir);
            Files.createDirectories(pathDir);

            Path filePath = pathDir.resolve(resultFileName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(file.getBytes());
            }

            return resultFileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сохранении файла: " + e.getMessage(), e);
        }
    }

    public ResponseEntity<?> getOutputFile(String filename, String subDir, MediaType mediaType) {
        Path filePath = Paths.get(UPLOAD_DIR + subDir + filename);

        try {
            byte[] data = Files.readAllBytes(filePath);
            Resource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);

        } catch (NoSuchFileException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Файл не найден: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при чтении файла");
        }
    }
}