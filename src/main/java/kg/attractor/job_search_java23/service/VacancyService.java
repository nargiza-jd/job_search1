package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    VacancyDto getVacancyById(String id);

    void createVacancy(VacancyDto vacancyDto);

    void updateVacancy(int id, VacancyDto vacancyDto);

    void deleteVacancy(int id);
}