package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllResumes();

    ResumeDto getResumeById(String resumeId);

    void createResume(ResumeDto resumeDto);

    void updateResume(int id, ResumeDto resumeDto);

    void deleteResume(int id);
}