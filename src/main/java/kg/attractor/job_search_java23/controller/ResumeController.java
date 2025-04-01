package kg.attractor.job_search_java23.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_java23.dto.ResumeDto;
import kg.attractor.job_search_java23.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("{id}")
    public ResumeDto getResumeById(@PathVariable String id) {
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public HttpStatus createResume(@RequestBody @Valid ResumeDto resumeDto) {
        resumeService.createResume(resumeDto);
        return HttpStatus.CREATED;
    }

    @PutMapping("{id}")
    public HttpStatus updateResume(@PathVariable int id, @RequestBody @Valid ResumeDto resumeDto) {
        resumeService.updateResume(id, resumeDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteResume(@PathVariable int id) {
        resumeService.deleteResume(id);
        return HttpStatus.OK;
    }
}