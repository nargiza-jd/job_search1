package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Название вакансии не должно быть пустым")
    private String title;

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @Min(value = 0, message = "Зарплата не может быть отрицательной")
    private Integer salary;

    @NotBlank(message = "Категория не должна быть пустой")
    private String category;

    @Min(value = 0, message = "Опыт от не может быть отрицательным")
    private Integer experienceFrom;

    @Min(value = 0, message = "Опыт до не может быть отрицательным")
    private Integer experienceTo;

    private boolean published;

    @NotNull(message = "Работодатель не должен быть пустым")
    private UserDto employer;
}
