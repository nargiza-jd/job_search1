package kg.attractor.job_search_java23.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private int id;
    private String title;
    private String category;
    private String description;
    private Integer expectedSalary;
    private String telegram;
    private String email;
    private String phone;
    private String facebook;
    private String linkedin;
    private boolean published;
    private Integer applicantId;

    @Override
    public String toString() {
        return String.format(
                "Резюме: <<%s>>%nКатегория: %s%nОписание: %s%nОжидаемая зарплата: %s%nСвязь: %s | %s | %s%nПрофили: fb: %s, linkedin: %s%nПублично: %s%nПользователь ID: %d",
                title,
                category,
                description,
                expectedSalary,
                telegram,
                email,
                phone,
                facebook,
                linkedin,
                published ? "да" : "нет",
                applicantId
        );
    }
}