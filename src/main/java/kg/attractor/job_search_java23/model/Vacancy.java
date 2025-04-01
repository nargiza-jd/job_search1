package kg.attractor.job_search_java23.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    private int id;
    private String username;
    private String description;
    private Integer salary;
    private int experienceFrom;
    private int experienceTo;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private int categoryId;
    private int authorId;

    @Override
    public String toString() {
        return String.format("Вакансия: %s, зарплата: %s", username, salary);
    }
}