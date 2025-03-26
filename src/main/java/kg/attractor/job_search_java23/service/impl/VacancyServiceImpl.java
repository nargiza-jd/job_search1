package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.VacancyDao;
import kg.attractor.job_search_java23.dto.VacancyDto;
import kg.attractor.job_search_java23.exceptions.VacancyNotFoundException;
import kg.attractor.job_search_java23.model.Vacancy;
import kg.attractor.job_search_java23.service.UserService;
import kg.attractor.job_search_java23.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;
    private final UserService userService;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        return vacancies.stream()
                .map(v -> VacancyDto.builder()
                        .id(v.getId())
                        .title(v.getTitle())
                        .description(v.getDescription())
                        .salary(v.getSalary())
                        .category(v.getCategory())
                        .experienceFrom(v.getExperienceFrom())
                        .experienceTo(v.getExperienceTo())
                        .published(v.isPublished())
                        .employer(userService.getUserById(v.getCompanyId()))
                        .build())
                .toList();
    }

    @Override
    public VacancyDto getVacancyById(String vacancyId) {
        int id = Integer.parseInt(vacancyId);
        Vacancy vacancy = vacancyDao.getVacancyById(id)
                .orElseThrow(VacancyNotFoundException::new);

        return VacancyDto.builder()
                .id(vacancy.getId())
                .title(vacancy.getTitle())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .category(vacancy.getCategory())
                .experienceFrom(vacancy.getExperienceFrom())
                .experienceTo(vacancy.getExperienceTo())
                .published(vacancy.isPublished())
                .employer(userService.getUserById(vacancy.getCompanyId()))
                .build();
    }

    @Override
    public void createVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = Vacancy.builder()
                .id(vacancyDao.getVacancies().size() + 1)
                .title(vacancyDto.getTitle())
                .description(vacancyDto.getDescription())
                .salary(vacancyDto.getSalary())
                .category(vacancyDto.getCategory())
                .experienceFrom(vacancyDto.getExperienceFrom())
                .experienceTo(vacancyDto.getExperienceTo())
                .published(vacancyDto.isPublished())
                .companyId(vacancyDto.getEmployer().getId())
                .build();

        vacancyDao.addVacancy(vacancy);
    }
}