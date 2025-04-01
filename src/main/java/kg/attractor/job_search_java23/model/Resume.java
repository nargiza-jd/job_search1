package kg.attractor.job_search_java23.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private int id;
    private String username;
    private Integer salary;
    private boolean isActive;
    private java.sql.Timestamp createdDate;
    private java.sql.Timestamp updateTime;
    private Integer applicantId;
    private Integer categoryId;

    @Override
    public String toString() {
        return String.format(
                "Резюме #%d%nИмя пользователя: %s%nЗарплата: %s%nАктивно: %s%nДата создания: %s%nОбновлено: %s%nКандидат ID: %d%nКатегория ID: %d",
                id,
                username,
                salary,
                isActive ? "да" : "нет",
                createdDate,
                updateTime,
                applicantId,
                categoryId
        );
    }
}