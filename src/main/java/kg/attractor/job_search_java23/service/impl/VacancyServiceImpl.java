package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.VacancyDao;
import kg.attractor.job_search_java23.dto.VacancyDto;
import kg.attractor.job_search_java23.exceptions.VacancyNotFoundException;
import kg.attractor.job_search_java23.model.Vacancy;
import kg.attractor.job_search_java23.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getAllVacancies() {
        return vacancyDao.getVacancies().stream()
                .map(this::mapToDto)
                .toList();
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
        Vacancy vacancy = Vacancy.builder()
                .username(dto.getUsername())
                .description(dto.getDescription())
                .salary(dto.getSalary())
                .experienceFrom(dto.getExperienceFrom())
                .experienceTo(dto.getExperienceTo())
                .isActive(dto.isActive())
                .categoryId(dto.getCategoryId())
                .authorId(dto.getAuthorId())
                .build();

        vacancyDao.save(vacancy);
    }

    @Override
    public void updateVacancy(int id, VacancyDto dto) {
        Vacancy vacancy = vacancyDao.getVacancyById(id)
                .orElseThrow(VacancyNotFoundException::new);

        vacancy.setUsername(dto.getUsername());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExperienceFrom(dto.getExperienceFrom());
        vacancy.setExperienceTo(dto.getExperienceTo());
        vacancy.setActive(dto.isActive());
        vacancy.setCategoryId(dto.getCategoryId());
        vacancy.setAuthorId(dto.getAuthorId());

        vacancyDao.updateVacancy(vacancy);
    }

    @Override
    public void deleteVacancy(int id) {
        vacancyDao.deleteVacancyById(id);
    }

    private VacancyDto mapToDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .username(vacancy.getUsername())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .experienceFrom(vacancy.getExperienceFrom())
                .experienceTo(vacancy.getExperienceTo())
                .isActive(vacancy.isActive())
                .categoryId(vacancy.getCategoryId())
                .authorId(vacancy.getAuthorId())
                .build();
    }
}