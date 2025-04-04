package kg.attractor.job_search_java23.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kg.attractor.job_search_java23.model.Resume;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileUtil {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String UPLOAD_DIR = "data/";

    public List<Resume> getResumes(String path) {
        Type listType = new TypeToken<Map<String, List<Resume>>>() {}.getType();
        try (Reader reader = new FileReader(UPLOAD_DIR + path)) {
            Map<String, List<Resume>> resumes = gson.fromJson(reader, listType);
            return resumes.getOrDefault("resumes", List.of());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

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