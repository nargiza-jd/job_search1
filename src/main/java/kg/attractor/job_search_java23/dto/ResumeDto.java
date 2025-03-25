package kg.attractor.job_search_java23.dto;

import kg.attractor.job_search_java23.model.Education;
import kg.attractor.job_search_java23.model.Experience;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Integer id;
    private String title;
    private String category;
    private Integer expectedSalary;

    private String telegram;
    private String email;
    private String phone;
    private String facebook;
    private String linkedin;

    private boolean published;

    private UserDto user;
    private List<Experience> experienceList;
    private List<Education> educationList;
}