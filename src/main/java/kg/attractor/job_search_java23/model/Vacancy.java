package kg.attractor.job_search_java23.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    private int id;
    private String title;
    private String description;
    private Integer salary;
    private String category;
    private String company;
    private String location;
    private int experienceFrom;
    private int experienceTo;
    private boolean published;
    private int companyId;
    private int authorId;

    @Override
    public String toString() {
        return String.format("Вакансия: %s (%s) в %s, %s", title, category, company, location);
    }
}