package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Название не должно быть пустым")
    private String title;

    @NotBlank(message = "Категория не должна быть пустой")
    private String category;

    @Min(value = 0, message = "Ожидаемая зарплата не может быть отрицательной")
    private Integer expectedSalary;

    private String telegram;

    @Email(message = "Неверный формат email")
    private String email;

    private String phone;
    private String facebook;
    private String linkedin;

    private boolean published;

    @NotNull(message = "Пользователь не должен быть пустым")
    private UserDto user;

    private List<Experience> experienceList;
    private List<Education> educationList;
}