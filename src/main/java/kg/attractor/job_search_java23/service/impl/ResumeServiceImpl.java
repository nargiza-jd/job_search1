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
public class ResumeServiceImpl implements ResumeService {

    private final List<Resume> resumes;

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
                        .user(userService.getUserById(String.valueOf(r.getUserId())))
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
                .user(userService.getUserById(String.valueOf(resume.getUserId())))
                .build();
    }

    @Override
    public void createResume(ResumeDto resumeDto) {
        List<Resume> resumes = resumeDao.getResumes();
        resumes.add(Resume.builder()
                .id(resumes.size() + 1)
                .title(resumeDto.getTitle())
                .category(resumeDto.getCategory())
                .expectedSalary(resumeDto.getExpectedSalary())
                .telegram(resumeDto.getTelegram())
                .email(resumeDto.getEmail())
                .phone(resumeDto.getPhone())
                .facebook(resumeDto.getFacebook())
                .linkedin(resumeDto.getLinkedin())
                .published(resumeDto.isPublished())
                .userId(resumeDto.getUser().getId())
                .build());
    }
}