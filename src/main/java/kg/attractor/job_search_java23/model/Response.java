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
    private boolean confirmation;

    @Override
    public String toString() {
        return String.format("Отклик на вакансию #%d от резюме #%d (Подтверждён: %s)",
                vacancyId, resumeId, confirmation ? "да" : "нет");
    }
}