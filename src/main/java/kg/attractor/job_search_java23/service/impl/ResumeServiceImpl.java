package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.ResumeDao;
import kg.attractor.job_search_java23.dto.ResumeDto;
import kg.attractor.job_search_java23.exceptions.ResumeNotFoundException;
import kg.attractor.job_search_java23.model.Resume;
import kg.attractor.job_search_java23.service.ResumeService;
import kg.attractor.job_search_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final UserService userService;

    @Override
    public List<ResumeDto> getAllResumes() {
        return resumeDao.getResumes().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ResumeDto getResumeById(String resumeId) {
        int id = Integer.parseInt(resumeId);
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return mapToDto(resume);
    }

    @Override
    public void createResume(ResumeDto resumeDto) {
        Resume resume = Resume.builder()
                .username(resumeDto.getTitle())
                .salary(resumeDto.getExpectedSalary())
                .isActive(resumeDto.isPublished())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .applicantId(resumeDto.getUser().getId())
                .categoryId(resumeDto.getCategoryId())
                .build();

        resumeDao.save(resume);
    }

    @Override
    public void updateResume(int id, ResumeDto resumeDto) {
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);

        resume.setUsername(resumeDto.getTitle());
        resume.setSalary(resumeDto.getExpectedSalary());
        resume.setActive(resumeDto.isPublished());
        resume.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        resume.setCategoryId(resumeDto.getCategoryId());

        resumeDao.updateResume(resume);
    }

    @Override
    public void deleteResume(int id) {
        resumeDao.deleteResumeById(id);
    }

    private ResumeDto mapToDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .title(resume.getUsername())
                .expectedSalary(resume.getSalary())
                .published(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .categoryId(resume.getCategoryId())
                .user(userService.getUserById(resume.getApplicantId()))
                .build();
    }
}