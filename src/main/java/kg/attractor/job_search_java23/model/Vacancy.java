package kg.attractor.job_search_java23.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    private int id;
    private String title;
    private String description;
    private Integer salary;
    private String category;
    private int experienceFrom;
    private int experienceTo;
    private boolean published;
    private int companyId;

    @Override
    public String toString() {
        return String.format("Вакансия: %s (%s)", title, category);
    }
}