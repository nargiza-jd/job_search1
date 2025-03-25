package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    VacancyDto getVacancyById(String vacancyId);

    void createVacancy(VacancyDto vacancyDto);
}