package kg.attractor.job_search_java23.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private int id;
    private String title;
    private String category;
    private Integer expectedSalary;
    private String telegram;
    private String email;
    private String phone;
    private String facebook;
    private String linkedin;
    private boolean published;
    private int userId;

    @Override
    public String toString() {
        return String.format(
                "Резюме: <<%s>>%nКатегория: %s%nОжидаемая зарплата: %s%nСвязь: %s | %s | %s%nПрофили: fb: %s, linkedin: %s%nПублично: %s%nПользователь ID: %d",
                title,
                category,
                expectedSalary,
                telegram,
                email,
                phone,
                facebook,
                linkedin,
                published ? "да" : "нет",
                userId
        );
    }
}