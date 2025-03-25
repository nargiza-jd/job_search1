package kg.attractor.job_search_java23.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int id;
    private int resumeId;
    private int vacancyId;
    private int applicantId;
    private int employerId;

    private int userId;
    private String message;

    @Override
    public String toString() {
        return String.format("Отклик от пользователя #%d на вакансию #%d: %s", userId, vacancyId, message);
    }
}