package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.ResumeDao;
import kg.attractor.job_search_java23.dto.ResumeDto;
import kg.attractor.job_search_java23.exceptions.ResumeNotFoundException;
import kg.attractor.job_search_java23.model.Resume;
import kg.attractor.job_search_java23.service.ResumeService;
import kg.attractor.job_search_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final UserService userService;

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getResumes();
        return resumes.stream()
                .map(r -> ResumeDto.builder()
                        .id(r.getId())
                        .title(r.getTitle())
                        .category(r.getCategory())
                        .expectedSalary(r.getExpectedSalary())
                        .telegram(r.getTelegram())
                        .email(r.getEmail())
                        .phone(r.getPhone())
                        .facebook(r.getFacebook())
                        .linkedin(r.getLinkedin())
                        .published(r.isPublished())
                        .user(userService.getUserById(r.getUserId())) // если метод принимает int
                        .build())
                .toList();
    }

    @Override
    public ResumeDto getResumeById(String resumeId) {
        int id = Integer.parseInt(resumeId);
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return ResumeDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .category(resume.getCategory())
                .expectedSalary(resume.getExpectedSalary())
                .telegram(resume.getTelegram())
                .email(resume.getEmail())
                .phone(resume.getPhone())
                .facebook(resume.getFacebook())
                .linkedin(resume.getLinkedin())
                .published(resume.isPublished())
                .user(userService.getUserById(resume.getUserId()))
                .build();
    }
}