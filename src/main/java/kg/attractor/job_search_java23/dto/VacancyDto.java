package kg.attractor.job_search_java23.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private Integer id;
    private String title;
    private String description;
    private Integer salary;
    private String category;
    private Integer experienceFrom;
    private Integer experienceTo;
    private boolean published;
    private UserDto employer;
}