package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат email")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,24}$",
            message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру, длина от 4 до 24 символов"
    )
    private String password;

    @Pattern(
            regexp = "^(\\+\\d{1,3})?\\d{9,12}$",
            message = "Некорректный формат телефона"
    )
    private String phone;

    private String profileImageUrl;

    @Pattern(regexp = "^(APPLICANT|EMPLOYER)$", message = "Role must be either APPLICANT or EMPLOYER")
    private String role;
}