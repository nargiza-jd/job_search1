package kg.attractor.job_search_java23.controller;

import kg.attractor.job_search_java23.dto.VacancyDto;
import kg.attractor.job_search_java23.model.Vacancy;
import kg.attractor.job_search_java23.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyDto> getVacancies() {
        return vacancyService.getAllVacancies();
    }

    @GetMapping("{vacancyId}")
    public ResponseEntity<String> getVacancy(@PathVariable("vacancyId") String vacancyId) {
        return ResponseEntity.ok(vacancyId);
    }

    @PostMapping
    public ResponseEntity<?> addVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.createVacancy(vacancyDto);
        return ResponseEntity.status(201).build();
    }
}