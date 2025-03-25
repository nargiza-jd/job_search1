package kg.attractor.job_search_java23.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    @GetMapping
    public ResponseEntity<?> getVacancies() {
        return ResponseEntity.ok("Vacancies");
    }

    @GetMapping("{vacancyId}")
    public ResponseEntity<String> getVacancy(@PathVariable("vacancyId") String vacancyId) {
        return ResponseEntity.ok(vacancyId);
    }

}