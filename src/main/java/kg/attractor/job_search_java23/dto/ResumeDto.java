package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Integer id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String title;

    @Min(value = 0, message = "Зарплата не может быть отрицательной")
    private Integer expectedSalary;

    private boolean published;

    private Timestamp createdDate;
    private Timestamp updateTime;

    @NotNull(message = "Категория не должна быть пустой")
    private Integer categoryId;

    @NotNull(message = "Пользователь не должен быть пустым")
    private UserDto user;
}