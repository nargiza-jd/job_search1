package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.VacancyDao;
import kg.attractor.job_search_java23.dto.VacancyDto;
import kg.attractor.job_search_java23.exceptions.VacancyNotFoundException;
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
    public VacancyDto getVacancyById(String id) {
        int vacancyId = Integer.parseInt(id);
        Vacancy vacancy = vacancyDao.getVacancyById(vacancyId)
                .orElseThrow(VacancyNotFoundException::new);
        return mapToDto(vacancy);
    }

    @Override
    public void createVacancy(VacancyDto dto) {
        List<Vacancy> vacancies = vacancyDao.getVacancies();

        vacancies.add(Vacancy.builder()
                .id(vacancies.size() + 1)
                .title(dto.getTitle())
                .category(dto.getCategory())
                .salary(dto.getSalary())
                .description(dto.getDescription())
                .experienceFrom(dto.getExperienceFrom())
                .experienceTo(dto.getExperienceTo())
                .published(dto.isPublished())
                .build());
    }

    @Override
    public void updateVacancy(int id, VacancyDto dto) {
        Vacancy vacancy = vacancyDao.getVacancyById(id)
                .orElseThrow(VacancyNotFoundException::new);

        vacancy.setTitle(dto.getTitle());
        vacancy.setCategory(dto.getCategory());
        vacancy.setSalary(dto.getSalary());
        vacancy.setDescription(dto.getDescription());
        vacancy.setExperienceFrom(dto.getExperienceFrom());
        vacancy.setExperienceTo(dto.getExperienceTo());
        vacancy.setPublished(dto.isPublished());
    }

    @Override
    public void deleteVacancy(int id) {
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        vacancies.removeIf(v -> v.getId() == id);
    }

    private VacancyDto mapToDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .title(vacancy.getTitle())
                .category(vacancy.getCategory())
                .salary(vacancy.getSalary())
                .description(vacancy.getDescription())
                .experienceFrom(vacancy.getExperienceFrom())
                .experienceTo(vacancy.getExperienceTo())
                .published(vacancy.isPublished())
                .build();
    }
}