package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.VacancyDao;
import kg.attractor.job_search_java23.dto.VacancyDto;
import kg.attractor.job_search_java23.model.Vacancy;
import kg.attractor.job_search_java23.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getAllVacancies() {
        return vacancyDao.getVacancies().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VacancyDto getVacancyById(String vacancyId) {
        return vacancyDao.getVacancyById(Integer.parseInt(vacancyId))
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public void createVacancy(VacancyDto dto) {
        Vacancy vacancy = Vacancy.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .salary(dto.getSalary())
                .category(dto.getCategory())
                .company(dto.getCompany())
                .location(dto.getLocation())
                .experienceFrom(dto.getExperienceFrom())
                .experienceTo(dto.getExperienceTo())
                .published(dto.isPublished())
                .companyId(dto.getCompanyId())
                .build();

        vacancyDao.save(vacancy);
    }

    private VacancyDto mapToDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .title(vacancy.getTitle())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .category(vacancy.getCategory())
                .company(vacancy.getCompany())
                .location(vacancy.getLocation())
                .experienceFrom(vacancy.getExperienceFrom())
                .experienceTo(vacancy.getExperienceTo())
                .published(vacancy.isPublished())
                .companyId(vacancy.getCompanyId())
                .build();
    }
}