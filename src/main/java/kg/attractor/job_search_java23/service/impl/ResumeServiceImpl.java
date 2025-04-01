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

//    @Override
//    public void createResume(ResumeDto resumeDto) {
//        List<Resume> resumes = resumeDao.getResumes();
//
//        Resume newResume = Resume.builder()
//                .id(resumes.size() + 1)
//                .title(resumeDto.getTitle())
//                .category(resumeDto.getCategory())
//                .expectedSalary(resumeDto.getExpectedSalary())
//                .telegram(resumeDto.getTelegram())
//                .email(resumeDto.getEmail())
//                .phone(resumeDto.getPhone())
//                .facebook(resumeDto.getFacebook())
//                .linkedin(resumeDto.getLinkedin())
//                .published(resumeDto.isPublished())
//                .applicantId(resumeDto.getUser().getId())
//                .build();
//
//        resumes.add(newResume);
//    }

    @Override
    public void createResume(ResumeDto resumeDto) {
        Resume resume = Resume.builder()
                .title(resumeDto.getTitle())
                .category(resumeDto.getCategory())
                .expectedSalary(resumeDto.getExpectedSalary())
                .telegram(resumeDto.getTelegram())
                .email(resumeDto.getEmail())
                .phone(resumeDto.getPhone())
                .facebook(resumeDto.getFacebook())
                .linkedin(resumeDto.getLinkedin())
                .published(resumeDto.isPublished())
                .applicantId(resumeDto.getUser().getId())
                .build();

        resumeDao.save(resume);
    }

    @Override
    public void updateResume(int id, ResumeDto resumeDto) {
        List<Resume> resumes = resumeDao.getResumes();

        Resume resume = resumes.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(ResumeNotFoundException::new);

        resume.setTitle(resumeDto.getTitle());
        resume.setCategory(resumeDto.getCategory());
        resume.setExpectedSalary(resumeDto.getExpectedSalary());
        resume.setTelegram(resumeDto.getTelegram());
        resume.setEmail(resumeDto.getEmail());
        resume.setPhone(resumeDto.getPhone());
        resume.setFacebook(resumeDto.getFacebook());
        resume.setLinkedin(resumeDto.getLinkedin());
        resume.setPublished(resumeDto.isPublished());

        if (resumeDto.getUser() != null) {
            resume.setApplicantId(resumeDto.getUser().getId());
        }
    }

    @Override
    public void deleteResume(int id) {
        List<Resume> resumes = resumeDao.getResumes();
        Resume resume = resumes.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(ResumeNotFoundException::new);

        resumes.remove(resume);
    }

    private ResumeDto mapToDto(Resume resume) {
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
                .user(userService.getUserById(resume.getApplicantId()))
                .build();
    }
}